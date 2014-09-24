package ph.txtdis.app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.OrderDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.exception.NotFoundException;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.input.MonetaryDisplay;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Channel;
import ph.txtdis.model.Ordered;
import ph.txtdis.model.Priced;
import ph.txtdis.model.VolumeDiscount;

public abstract class AbstractOrderApp<E extends Ordered<D>, D extends Priced, O extends OrderDTO<E, D>> extends
        AbstractIdApp<E> implements OrderApp<D> {

    public AbstractOrderApp(String module, String abbr) {
        super(module, abbr);
    }

    protected BigDecimal price, subtotal;
    protected BigDecimal vat = BigDecimal.ZERO;
    protected BigDecimal vatable = BigDecimal.ZERO;
    protected BigDecimal total = BigDecimal.ZERO;
    protected CustomerDTO customer;
    protected ItemDTO item;
    protected D detailTableItem;
    protected O orderDTO;
    protected List<D> detailTableItems;

    protected DatePicker datePicker;
    protected MonetaryDisplay vatableField, vatField, totalField;
    protected HBox partnerBox;
    protected IdField idField, partnerIdField;
    protected Label idLabel, dateLabel, partnerLabel, partnerAddressLabel, remarkLabel;
    protected StringField partnerNameField, partnerAddressField, remarkField;
    protected TableView<D> detailTable;

    @Override
    protected void setDTO() {
        setCustomerDTO();
        setItemDTO();
    }

    @Override
    public void setFocus() {
        datePicker.requestFocus();
    }

    @Override
    protected Node[] addVBoxNodes() {

        createOrderedLabeledInputs();
        createDetailTable();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        addGridPaneNodes(gridPane);

        HBox box = new HBox(detailTable);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        VBox routingBox = new VBox(gridPane, box);
        return new Node[] { routingBox };
    }

    protected void createOrderedLabeledInputs() {
        idLabel = new Label("ID No.");
        idField = new IdField(orderDTO.getId());
        idField.setEditable(false);

        dateLabel = new Label("Date");
        datePicker = new DatePicker(orderDTO.getOrderDate());

        partnerLabel = new Label("Partner No.");
        partnerIdField = new IdField(orderDTO.getPartnerId());
        partnerNameField = new StringField(orderDTO.getPartnerName());
        partnerBox = new HBox(partnerLabel, partnerIdField, partnerNameField);

        partnerAddressLabel = new Label("Address");
        partnerAddressField = new StringField(orderDTO.getPartnerAddress());

        remarkLabel = new Label("Remarks");
        remarkField = new StringField(orderDTO.getRemarks());
    }

    protected void addGridPaneNodes(GridPane gridPane) {
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(dateLabel, 2, 0);
        gridPane.add(datePicker, 3, 0);
        gridPane.add(partnerLabel, 4, 0);
        gridPane.add(partnerBox, 5, 0);

        gridPane.add(partnerAddressLabel, 0, 1);
        gridPane.add(partnerAddressField, 1, 1, 5, 1);

        gridPane.add(remarkLabel, 0, 2);
        gridPane.add(remarkField, 1, 2, 5, 2);
    }

    @Override
    protected void setBindings() {
        buttons.get("save").disableProperty().bind(FX.isEmpty(detailTable).or(FX.isEmpty(idField).not()));

        partnerIdField.disableProperty().bind(FX.isEmpty(datePicker));

        partnerNameField.setEditable(false);
        partnerNameField.setFocusTraversable(false);

        partnerAddressField.setEditable(false);
        partnerAddressField.setFocusTraversable(false);

        remarkField.disableProperty().bind(FX.isEmpty(partnerNameField));
        detailTable.disableProperty().bind(FX.isEmpty(partnerNameField));
    }

    @Override
    protected void setListeners() {
        partnerIdField.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                validatePartnerId(partnerIdField.getIdNo());
        });
    }

    private void validatePartnerId(int id) {
        if (customer.exists(id))
            actWhenFound(id);
        else
            throwNotFoundException(id);
    }

    private void throwNotFoundException(int id) {
        try {
            throw new NotFoundException("Partner ID No. " + id);
        } catch (Exception e) {
            actOnError(this, e);
        }
    }

    protected void actWhenFound(int id) {
        customer.setById(id);
        partnerNameField.setText(customer.getName());
        partnerAddressField.setText(customer.getSpecific());
    }

    protected void actOnError(Stage stage, Exception e) {
        new ErrorDialog(stage, e.getMessage());
        partnerIdField.clear();
        partnerNameField.setText("");
        partnerAddressField.setText("");
        tableItems().clear();
    }

    @Override
    public void save() throws InvalidException {
        orderDTO.setPartner(customer.get(partnerIdField.getIdNo()));
        orderDTO.setRoute(customer.getLatestRoute(getPickerDate()));
        orderDTO.setCredit(customer.getLatestCreditDetail(getPickerDate()));
        orderDTO.setDiscount(customer.getLatestCustomerDiscount(getPickerDate()));
        orderDTO.setAmount(totalField.getValue());
        orderDTO.setOrderDate(datePicker.getValue());
        orderDTO.setDetails(tableItems());
        orderDTO.save();
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void refresh() {
        idField.setIdNo(orderDTO.getId());
        datePicker.setValue(orderDTO.getOrderDate());
        remarkField.setText(orderDTO.getRemarks());
        detailTableItems = orderDTO.getDetails();
        populateFields((OrderDTO) orderDTO);
        // if (orderDTO.isStockTakeOnGoing())
        // throw new
        // InvalidException("No posting's allowed;\nstock take's on-going");
        super.refresh();
    }

    protected void populateFields(OrderDTO<Ordered<Priced>, Priced> dto) {
        populatePartnerRelatedFields(dto);
        createDetailTableItems(dto);
        updateDetailTableItems();
    }

    private void populatePartnerRelatedFields(OrderDTO<Ordered<Priced>, Priced> dto) {
        partnerIdField.setIdNo(dto.getPartnerId());
        partnerNameField.setText(dto.getPartnerName());
        partnerAddressField.setText(dto.getPartnerAddress());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void createDetailTableItems(OrderDTO<Ordered<Priced>, Priced> dto) {
        detailTableItems = orderDTO.getDetails();
        iterateDetailTableItems((List<Priced>) detailTableItems);
    }

    @Override
    public void iterateDetailTableItems(List<Priced> details) {
        zeroTotals();
        for (Priced priced : details)
            setValues(priced);
        updateTotals();
    }

    private void zeroTotals() {
        vatable = BigDecimal.ZERO;
        vat = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
    }

    protected void setValues(Priced priced) {
        setDetail(priced);
        item.set(detailTableItem.getItem());
        setPrice();

        subtotal = price.multiply(detailTableItem.getQty());
        total = total.add(subtotal);
        vatable = total.divide(new BigDecimal(1.12), 2, RoundingMode.HALF_UP);
        vat = total.subtract(vatable);

        detailTableItem.setPrice(price);
        detailTableItem.setSubtotal(price.multiply(detailTableItem.getQty()));
    }

    private void updateTotals() {
        vatableField.setValue(vatable);
        vatField.setValue(vat);
        totalField.setValue(total);
    }

    private void updateDetailTableItems() {
        tableItems().clear();
        tableItems().addAll(detailTableItems);
        detailTable.scrollTo(tableItems().size() - 1);
    }

    private ObservableList<D> tableItems() {
        return detailTable.getItems();
    }

    @Override
    public LocalDate getPickerDate() {
        return datePicker.getValue();
    }

    @Override
    public void setPrice() {
        price = (item.getLatestSellingPrice(getPickerDate()).subtract(getVolumeDiscount()))
                .multiply(getQtyPerUomUnit());
    }

    private BigDecimal getVolumeDiscount() {
        VolumeDiscount volumeDiscount = item.getLatestVolumeDiscount(getPickerDate());
        Channel limit = volumeDiscount.getChannelLimit();
        if ((limit == null || limit.equals(customer.get().getChannel()))
                && detailTableItem.getQty().multiply(getQtyPerUomUnit())
                        .compareTo(new BigDecimal(volumeDiscount.getCutOff())) >= 0)
            return volumeDiscount.getDiscount();
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getQtyPerUomUnit() {
        return item.getQtyPerUomMap().get(detailTableItem.getUom());
    }

    @Override
    protected Node[] addSummaryNodes() {
        Label vatableLabel = new Label("Vatable");
        vatableField = new MonetaryDisplay(vatable);
        Label vatLabel = new Label("VAT");
        vatField = new MonetaryDisplay(vat);
        Label totalLabel = new Label("Total");
        totalField = new MonetaryDisplay(total);
        return new Node[] { vatableLabel, vatableField, vatLabel, vatField, totalLabel, totalField };
    }
}
