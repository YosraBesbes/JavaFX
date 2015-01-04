package ph.txtdis.app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
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
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.display.CurrencyDisplay;
import ph.txtdis.fx.display.StringDisplay;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Channel;
import ph.txtdis.model.Ordered;
import ph.txtdis.model.Priced;
import ph.txtdis.model.VolumeDiscount;

public abstract class AbstractOrderApp<E extends Ordered<D>, D extends Priced, O extends OrderDTO<E, D>> extends
        AbstractIdApp<E> implements OrderApp<D> {

    protected BigDecimal price, subtotal;
    protected BigDecimal vat = BigDecimal.ZERO;
    protected BigDecimal vatable = BigDecimal.ZERO;
    protected BigDecimal total = BigDecimal.ZERO;
    protected CustomerDTO customer;
    protected ItemDTO item;
    protected D detailTableItem;
    protected O orderDTO;
    protected List<D> detailTableItems;

    protected ObservableList<String> discountList;

    protected ComboBox<String> discountCombo;
    protected DatePicker datePicker;
    protected CurrencyDisplay vatableField, vatField, totalField;
    protected HBox partnerBox;
    protected IdField idField, partnerIdField;
    protected Label idLabel, dateLabel, partnerLabel, partnerAddressLabel, remarkLabel;
    protected StringDisplay partnerNameField, partnerAddressField;
    protected StringField remarkField;
    protected TableView<D> detailTable;

    private VolumeDiscount latestVolumeDiscount;

    public AbstractOrderApp(String module, String abbr) {
        super(module, abbr);
    }

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
        datePicker = new DatePicker(getOrderDate());

        partnerLabel = new Label("Partner No.");
        partnerIdField = new IdField(orderDTO.getPartnerId());
        partnerNameField = new StringDisplay(orderDTO.getPartnerName());
        partnerBox = new HBox(partnerLabel, partnerIdField, partnerNameField);

        partnerAddressLabel = new Label("Address");
        partnerAddressField = new StringDisplay(orderDTO.getPartnerAddress());

        remarkLabel = new Label("Remarks");
        remarkField = new StringField(orderDTO.getRemarks());
    }

    protected LocalDate getOrderDate() {
        return orderDTO.getOrderDate();
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
        buttons.get("save").disableProperty()
                .bind(FX.isEmpty(detailTable).or(FX.isEmpty(encoderDisplay).not()).or(FX.isEmpty(datePicker)));
        partnerIdField.disableProperty().bind(FX.isEmpty(datePicker));
        remarkField.disableProperty().bind(FX.isEmpty(partnerNameField));
        detailTable.disableProperty().bind(FX.isEmpty(partnerNameField));
    }

    @Override
    protected void setListeners() {
        partnerIdField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB)
                validatePartnerId(partnerIdField.getIdNo());
        });

        datePicker.setOnAction(event -> validateDate(datePicker.getValue()));
    }

    private void validateDate(LocalDate date) {
        if (date != null && idField.getText().isEmpty() && date.isBefore(LocalDate.now()))
            handleError(this, "Date cannot be in the past");
    }

    private void validatePartnerId(int id) {
        if (customer.exists(id))
            handleFoundId(id);
        else
            handleError(this, "Partner No." + id + "\nis not in this database");
    }

    protected void handleFoundId(int id) {
        customer.setById(id);
        partnerNameField.setText(customer.getName());
        partnerAddressField.setText(customer.getSpecific());
    }

    protected void handleError(Stage stage, String msg) {
        new ErrorDialog(stage, msg);
        orderDTO.reset();
        refresh();
    }

    @Override
    public void save() throws InvalidException {
        orderDTO.setPartner(customer.get(partnerIdField.getIdNo()));
        orderDTO.setRoute(customer.getLatestRoute(getPickerDate()));
        orderDTO.setCredit(customer.getLatestCreditDetail(getPickerDate()));
        orderDTO.setTotalValue(totalField.getValue());
        orderDTO.setOrderDate(datePicker.getValue());
        orderDTO.setDetails(tableItems());
        orderDTO.save();
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void refresh() {
        updateInputFields();
        populateFields((OrderDTO) orderDTO);
        super.refresh();
    }

    protected void updateInputFields() {
        idField.setIdNo(orderDTO.getId());
        datePicker.setValue(getOrderDate());
        remarkField.setText(orderDTO.getRemarks());
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
        discountList = FXCollections.observableArrayList();
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
        vatable = total.divide(new BigDecimal("1.12"), 2, RoundingMode.HALF_EVEN);
        vat = total.subtract(vatable);
        detailTableItem.setPrice(price);
    }

    private void updateTotals() {
        discountCombo.setItems(discountList);
        discountCombo.getSelectionModel().select(0);

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
        if (latestVolumeDiscount != null && (getChannelLimit() == null || isCustomersChannelSameAsLimits())
                && hasItemMetVolumeDiscountTarget())
            return latestVolumeDiscount.getDiscount();
        else
            return BigDecimal.ZERO;
    }

    private boolean isCustomersChannelSameAsLimits() {
        return getChannelLimit().equals(customer.get().getChannel());
    }

    private boolean hasItemMetVolumeDiscountTarget() {
        return detailTableItem.getQty().multiply(getQtyPerUomUnit())
                .compareTo(new BigDecimal(latestVolumeDiscount.getCutOff())) >= 0;
    }

    private Channel getChannelLimit() {
        return latestVolumeDiscount.getChannelLimit();
    }

    @Override
    public BigDecimal getQtyPerUomUnit() {
        return item.getQtyPerUomMap().get(detailTableItem.getUom());
    }

    @Override
    protected Node[] addSummaryNodes() {
        Label discountLabel = new Label("Discount");
        discountCombo = new ComboBox<>(discountList);
        Label vatableLabel = new Label("Vatable");
        vatableField = new CurrencyDisplay(vatable);
        Label vatLabel = new Label("VAT");
        vatField = new CurrencyDisplay(vat);
        Label totalLabel = new Label("Total");
        totalField = new CurrencyDisplay(total);
        return new Node[] { discountLabel, discountCombo, vatableLabel, vatableField, vatLabel, vatField, totalLabel,
                totalField };
    }
}
