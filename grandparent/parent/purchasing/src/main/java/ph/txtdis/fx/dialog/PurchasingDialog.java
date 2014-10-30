package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.app.OrderApp;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.PurchasingDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.fx.display.LabeledDisplay;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledIdNameField;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Item;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
import ph.txtdis.model.Quality;
import ph.txtdis.type.UomType;

public class PurchasingDialog extends AbstractFieldDialog<PurchasingDetail, PurchasingDTO> {

    private LabeledIdNameField itemField;
    private LabeledComboBox<UomType> uomCombo;
    private LabeledDisplay oldDaysLevelDisplay, newDaysLevelDisplay;
    private LabeledDecimalField qtyField;
    private LabeledStringField reasonField;
    private ItemDTO itemDTO;
    private PurchasingDTO dto;

    public PurchasingDialog(Stage stage, PurchasingDTO dto) {
        super("Purchasing", stage, dto);
        setListeners();
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        itemField = new LabeledIdNameField("Item ID No.", 180);
        oldDaysLevelDisplay = new LabeledDisplay("Days Level Now", 80);
        uomCombo = new LabeledComboBox<>("UOM", UomType.values());
        qtyField = new LabeledDecimalField("Quantity");
        newDaysLevelDisplay = new LabeledDisplay("New Days Level", 80);
        reasonField = new LabeledStringField("Justification");
        return Arrays.asList(itemField, oldDaysLevelDisplay, uomCombo, qtyField, newDaysLevelDisplay, reasonField);
    }

    @Override
    protected BooleanBinding getAddButtonBindings() {
        return areInputFieldsEmpty().or(isExcessivePurchaseNotJustified());
    }

    private BooleanBinding areInputFieldsEmpty() {
        return itemField.isEmpty().or(uomCombo.isEmpty()).or(qtyField.isEmpty());
    }

    private BooleanBinding isExcessivePurchaseNotJustified() {
        return reasonField.isEmpty().and(isOverStocked(newDaysLevelDisplay).or(isOverStocked(oldDaysLevelDisplay)));
    }

    private BooleanBinding isOverStocked(LabeledDisplay display) {
        return display.getTextField().textProperty().isEqualTo(">365");
    }

    private void setListeners() {
        setDTOs();
        setItemFieldListener();
        setQtyFieldListener();
    }

    private void setDTOs() {
        itemDTO = App.getContext().getBean(ItemDTO.class);
        dto = object;
    }

    private void setItemFieldListener() {
        itemField.getIdField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                verifyExistence(itemField.getValue());
        });
    }

    private void setQtyFieldListener() {
        qtyField.getDecimalField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                showDaysLevel(qtyField.getValue());
        });
    }

    private void verifyExistence(int id) {
        if (itemDTO.exists(id))
            populateItemRelatedFields(id);
        else
            showErrorDialog(id);
    }

    private void populateItemRelatedFields(int id) {
        itemDTO.setById(id);
        uomCombo.setItems(itemDTO.getPurchasingUoms());
        itemField.getNameField().setText(itemDTO.getName());
        showDaysLevel();
    }

    private void showErrorDialog(int id) {
        new ErrorDialog(stage, "Item ID No. " + id + "\nis not in this database");
        inputNodes.forEach(inputNode -> inputNode.reset());
    }

    private void showDaysLevel(BigDecimal qty) {
        newDaysLevelDisplay.setText(getDaysLevel(qty));
    }

    private void showDaysLevel() {
        oldDaysLevelDisplay.setText(getDaysLevel(BigDecimal.ZERO));
    }

    private String getDaysLevel(BigDecimal qty) {
        int daysLevel = dto.getDaysLevel(itemDTO.get(), qty);
        return daysLevel > 365 ? ">365" : String.valueOf(daysLevel);
    }

    @Override
    protected PurchasingDetail createEntity(PurchasingDTO dto, List<InputNode<?>> inputNodes) {
        return new PurchasingDetail(getEntity(dto), getItem(), getUom(), getQty(), getPrice(), getQuality(),
                getDaysLevel(), getJustification());
    }

    private Purchasing getEntity(PurchasingDTO dto) {
        return dto.get();
    }

    private Item getItem() {
        return itemDTO.get();
    }

    private UomType getUom() {
        return getInputAtRow(2);
    }

    private BigDecimal getQty() {
        return getInputAtRow(3);
    }

    private BigDecimal getPrice() {
        return getPricePerPC().multiply(getQtyInPCs());
    }

    private BigDecimal getPricePerPC() {
        return itemDTO.getLatestPurchasePrice(getDate());
    }

    private LocalDate getDate() {
        return ((OrderApp<?>) stage).getPickerDate();
    }

    private BigDecimal getQtyInPCs() {
        return itemDTO.getQtyPerUomMap().get(getUom());
    }

    private Quality getQuality() {
        return App.getContext().getBean(QualityRated.class).good();
    }

    private String getDaysLevel() {
        return getInputAtRow(4);
    }

    private String getJustification() {
        return getInputAtRow(5);
    }
}
