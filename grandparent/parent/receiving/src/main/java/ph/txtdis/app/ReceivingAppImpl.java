package ph.txtdis.app;

import java.time.LocalDate;
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
import ph.txtdis.exception.InvalidException;
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
import ph.txtdis.model.Users;
import ph.txtdis.type.CustomerType;
import ph.txtdis.type.ReceivingReferenceType;
import ph.txtdis.util.Util;

public class ReceivingAppImpl extends AbstractOrderApp<Receiving, ReceivingDetail, ReceivingDTO> {

    private PurchasingDTO purchasing;
    private BookingDTO booking;
    private UserDTO user;

    private ComboBox<ReceivingReferenceType> referenceCombo;
    private ComboBox<Users> checkerCombo;
    private HBox referenceBox;
    private IdField referenceIdField;
    private IntegerField partnerReferenceIdField;
    private Label partnerReferenceIdLabel, checkerLabel, referenceLabel;

    public ReceivingAppImpl() {
        super("Receiving", "R/R");
    }

    @Override
    protected void setDTO() {
        dto = orderDTO = App.getContext().getBean(ReceivingDTO.class);
        booking = App.getContext().getBean(BookingDTO.class);
        purchasing = App.getContext().getBean(PurchasingDTO.class);
        user = App.getContext().getBean(UserDTO.class);
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

        datePicker.setValue(getOrderDate());
        datePicker.setDisable(true);
        datePicker.setStyle("-fx-opacity: 1");
    }

    @Override
    protected LocalDate getOrderDate() {
        return orderDTO.getOrderDate() == null ? LocalDate.now() : orderDTO.getOrderDate();
    }

    @Override
    protected void addGridPaneNodes(GridPane gridPane) {
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(dateLabel, 2, 0);
        gridPane.add(datePicker, 3, 0);
        gridPane.add(referenceLabel, 4, 0);
        gridPane.add(referenceBox, 5, 0);

        gridPane.add(checkerLabel, 0, 1);
        gridPane.add(checkerCombo, 1, 1);
        gridPane.add(partnerReferenceIdLabel, 2, 1);
        gridPane.add(partnerReferenceIdField, 3, 1);
        gridPane.add(partnerLabel, 4, 1);
        gridPane.add(partnerBox, 5, 1);

        gridPane.add(partnerAddressLabel, 0, 2);
        gridPane.add(partnerAddressField, 1, 2, 5, 1);

        gridPane.add(remarkLabel, 0, 3);
        gridPane.add(remarkField, 1, 3, 5, 1);
    }

    @Override
    public void setFocus() {
        referenceCombo.requestFocus();
    }

    @Override
    public void save() throws InvalidException {
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
                priced.getQuality());
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
    protected void setBindings() {
        super.setBindings();
        partnerIdField.setEditable(false);
        partnerIdField.setFocusTraversable(false);

        referenceIdField.disableProperty().bind(FX.isEmpty(referenceCombo));
        checkerCombo.disableProperty().bind(FX.isEmpty(partnerNameField));
        partnerReferenceIdField.disableProperty().bind(FX.isEmpty(partnerNameField));
    }

    @Override
    protected void setListeners() {
        referenceIdField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB)
                try {
                    verifyReference(referenceIdField.getIdNo());
                } catch (Exception e) {
                    new ErrorDialog(this, e.getMessage());
                    refresh();
                }
        });
    }

    private void verifyReference(int id) throws InvalidException {
        if (isAPurchase())
            verifyPurchaseOrder(id);
        else
            verifySalesOrder(id);
    }

    private void verifyPurchaseOrder(int id) throws InvalidException {
        if (purchasing.exists(id)) {
            checkPurchaseOrderHasBeenReceived(id);
        } else
            throw new NotFoundException("P/O No. " + id);
    }

    private void checkPurchaseOrderHasBeenReceived(int id) throws InvalidException {
        purchasing.setById(id);
        handlePurchaseOrderHasBeenReceivedCheck(getReceivedDate());
    }

    private LocalDate getReceivedDate() {
        return orderDTO.getReceivedDateFromPurchaseOrder(purchasing.getId());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void handlePurchaseOrderHasBeenReceivedCheck(LocalDate receivedDate) throws InvalidException {
        if (receivedDate != null && receivedDate.isBefore(LocalDate.now()))
            throw new InvalidException(receivedBeforeToday(receivedDate));
        else
            populateFields((OrderDTO) purchasing);
    }

    private String receivedBeforeToday(LocalDate receivedDate) {
        return "P/O No. " + purchasing.getId() + " has been served;\nit was received last "
                + Util.formatDate(receivedDate);
    }

    private void verifySalesOrder(int id) throws InvalidException {
        if (booking.exists(id))
            checkSalesOrderWasPicked(id);
        else
            throw new NotFoundException("S/O No. " + id);
    }

    private void checkSalesOrderWasPicked(int id) throws InvalidException {
        booking.setById(id);
        handleSalesOrderHasBeenPickedCheck(getPickDate());
    }

    private LocalDate getPickDate() {
        return orderDTO.getPickDateFromSalesOrder(booking.get());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void handleSalesOrderHasBeenPickedCheck(LocalDate pickDate) throws InvalidException {
        if (pickDate == null)
            throw new InvalidException("S/O No. " + booking.getId() + "\nhas not been picked");
        else if (pickDate.isBefore(LocalDate.now()))
            throw new InvalidException(pickedBeforeToday(pickDate));
        else
            populateFields((OrderDTO) booking);
    }

    private String pickedBeforeToday(LocalDate pickDate) {
        return "S/O No. " + booking.getId() + "\nis not part of the delivery today;\nit was picked last "
                + Util.formatDate(pickDate);
    }
}
