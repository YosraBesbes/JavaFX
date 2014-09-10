package ph.txtdis.app;

import java.util.ArrayList;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ph.txtdis.App;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.OrderDTO;
import ph.txtdis.dto.PurchasingDTO;
import ph.txtdis.dto.ReceivingDTO;
import ph.txtdis.dto.UserDTO;
import ph.txtdis.exception.NotFoundException;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.input.IntegerField;
import ph.txtdis.fx.table.ReceivingDetailTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Ordered;
import ph.txtdis.model.Priced;
import ph.txtdis.model.Receiving;
import ph.txtdis.model.ReceivingDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.type.CustomerType;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.ReceivingReferenceType;

public class ReceivingAppImpl extends AbstractOrderApp<Receiving, ReceivingDetail, ReceivingDTO> {

    private PurchasingDTO purchasing;
    private BookingDTO booking;
    private UserDTO user;

    private ComboBox<ReceivingReferenceType> referenceCombo;
    private ComboBox<SystemUser> checkerCombo;
    private HBox referenceBox;
    private IdField referenceIdField;
    private IntegerField partnerReferenceIdField;
    private Label partnerReferenceIdLabel, checkerLabel, referenceLabel;

    public ReceivingAppImpl() {
        super("Receiving", "R/R");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(ReceivingDTO.class);
        booking = App.getContext().getBean(BookingDTO.class);
        purchasing = App.getContext().getBean(PurchasingDTO.class);
        user = App.getContext().getBean(UserDTO.class);
        orderDTO = (ReceivingDTO) dto;
        super.setDTO();
    }

    @Override
    public void setCustomerDTO() {
        customer = App.getContext().getBean(CustomerDTO.class);
    }

    @Override
    public void setItemDTO() {
        item = App.getContext().getBean(ItemDTO.class);
    }

    @Override
    public void createDetailTable() {
        detailTable = new ReceivingDetailTable(this, orderDTO).getTable();
    }

    @Override
    protected void createOrderedLabeledInputs() {
        super.createOrderedLabeledInputs();
        partnerReferenceIdLabel = new Label("Partner Reference ID");
        partnerReferenceIdField = new IntegerField(orderDTO.getPartnerReferenceId());

        checkerLabel = new Label("Checker");
        checkerCombo = FX.createComboBox(user.list(), orderDTO.getChecker());

        referenceLabel = new Label("Reference");
        referenceCombo = FX.createComboBox(ReceivingReferenceType.values(), orderDTO.getReference());
        referenceIdField = new IdField(orderDTO.getReferenceId());
        referenceBox = new HBox(referenceCombo, referenceIdField);
    }

    @Override
    protected void addGridPaneNodes(GridPane gridPane) {
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(dateLabel, 2, 0);
        gridPane.add(datePicker, 3, 0);
        gridPane.add(partnerReferenceIdLabel, 4, 0);
        gridPane.add(partnerReferenceIdField, 5, 0);

        gridPane.add(checkerLabel, 0, 1);
        gridPane.add(checkerCombo, 1, 1);
        gridPane.add(referenceLabel, 2, 1);
        gridPane.add(referenceBox, 3, 1);
        gridPane.add(partnerLabel, 4, 1);
        gridPane.add(partnerBox, 5, 1);

        gridPane.add(partnerAddressLabel, 0, 2);
        gridPane.add(partnerAddressField, 1, 2, 5, 1);

        gridPane.add(remarkLabel, 0, 3);
        gridPane.add(remarkField, 1, 3, 5, 1);
    }

    @Override
    protected String getTitleName() {
        return App.title();
    }

    @Override
    public void save() {
        orderDTO.setPartner(customer.get(partnerIdField.getIdNo()));
        orderDTO.setOrderDate(datePicker.getValue());
        orderDTO.setChecker(checkerCombo.getValue());
        orderDTO.setReference(referenceCombo.getValue());
        orderDTO.setReferenceId(referenceIdField.getIdNo());
        orderDTO.setPartnerReferenceId(partnerReferenceIdField.getLong());
        orderDTO.setDetails(detailTable.getItems());
        super.save();
    }

    @Override
    public void createDetailTableItems(OrderDTO<Ordered<Priced>, Priced> dto) {
        detailTableItems = new ArrayList<>();
        iterateDetailTableItems(dto.getDetails());
    }

    @Override
    protected void setValues(Priced priced) {
        super.setValues(priced);
        detailTableItems.add(detailTableItem);
    }

    @Override
    public void setDetail(Priced priced) {
        detailTableItem = new ReceivingDetail(orderDTO.get(), priced.getItem(), priced.getUom(), priced.getQty(),
                QualityType.GOOD);
    }

    @Override
    public void setPrice() {
        if (isAPurchase() && customer.getType() == CustomerType.VENDOR)
            price = item.getLatestPurchasePrice(getPickerDate()).multiply(getQtyPerUomUnit());
        else
            super.setPrice();
    }

    private boolean isAPurchase() {
        return referenceCombo.getValue() == ReceivingReferenceType.PO;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void refresh() {
        partnerReferenceIdField.setLong(orderDTO.getPartnerReferenceId());
        checkerCombo.setValue(orderDTO.getChecker());
        referenceCombo.setValue(orderDTO.getReference());
        referenceIdField.setIdNo(orderDTO.getReferenceId());
        populateFields((OrderDTO) orderDTO);
        super.refresh();
    }

    @Override
    protected void setDisableBindings() {
        super.setDisableBindings();
        partnerIdField.setEditable(false);
        partnerIdField.setFocusTraversable(false);

        partnerReferenceIdField.disableProperty().bind(FX.isEmpty(datePicker));
        checkerCombo.disableProperty().bind(FX.isEmpty(datePicker));
        referenceCombo.disableProperty().bind(FX.isEmpty(checkerCombo));
        referenceIdField.disableProperty().bind(FX.isEmpty(referenceCombo));
    }

    @Override
    protected void setListeners() {
        referenceIdField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB)
                try {
                    verifyReference(referenceIdField.getIdNo());
                } catch (Exception e) {
                    new ErrorDialog(this, e.getMessage());
                }
        });
    }

    private void verifyReference(int id) throws NotFoundException {
        if (isAPurchase())
            verifyPurchaseOrder(id);
        else
            verifySalesOrder(id);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void verifyPurchaseOrder(int id) throws NotFoundException {
        if (purchasing.exists(id)) {
            purchasing.setId(id);
            populateFields((OrderDTO) purchasing);
        } else
            throw new NotFoundException("P/O No. " + id);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void verifySalesOrder(int id) throws NotFoundException {
        if (booking.exists(id)) {
            booking.setId(id);
            populateFields((OrderDTO) booking);
        } else
            throw new NotFoundException("S/O No. " + id);
    }
}
