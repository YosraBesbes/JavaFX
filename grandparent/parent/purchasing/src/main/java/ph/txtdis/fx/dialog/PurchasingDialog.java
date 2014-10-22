package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.app.OrderApp;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.PurchasingDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.fx.display.LabeledIntegerDisplay;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledIdNameField;
import ph.txtdis.model.Item;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
import ph.txtdis.model.Quality;
import ph.txtdis.type.UomType;

public class PurchasingDialog extends AbstractFieldDialog<PurchasingDetail, PurchasingDTO> {

    private LabeledComboBox<UomType> uomCombo;
    private LabeledIdNameField itemField;
    private LabeledIntegerDisplay daysLevelOldDisplay, daysLevelNewDisplay;
    private LabeledDecimalField qtyField;
    private ItemDTO itemDTO;

    public PurchasingDialog(Stage stage, PurchasingDTO dto) {
        super("Purchasing", stage, dto);
        setListeners();
    }

    @Override
    protected List<InputNode<?>> addNodes() {

        itemField = new LabeledIdNameField("Item ID No.", 180);
        daysLevelOldDisplay = new LabeledIntegerDisplay("Days Level Now");
        uomCombo = new LabeledComboBox<>("UOM", UomType.values());
        qtyField = new LabeledDecimalField("Quantity");
        daysLevelNewDisplay = new LabeledIntegerDisplay("New Days Level");

        return Arrays.asList(itemField, daysLevelOldDisplay, uomCombo, qtyField, daysLevelNewDisplay);
    }

    private void setListeners() {
        itemDTO = App.getContext().getBean(ItemDTO.class);

        itemField.getIdField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                verifyExistence(itemField.getValue());
        });

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
        daysLevelNewDisplay.setInt(computeDaysLevel(qty));
    }

    private void showDaysLevel() {
        daysLevelOldDisplay.setInt(computeDaysLevel(BigDecimal.ZERO));
    }

    private int computeDaysLevel(BigDecimal qty) {
        return 999;
    }

    @Override
    protected PurchasingDetail createEntity(PurchasingDTO dto, List<InputNode<?>> inputNodes) {
        return new PurchasingDetail(getEntity(dto), getItem(), getUom(), getQty(), getPrice(), getQuality(),
                getDaysLevel());
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

    private int getDaysLevel() {
        return getInputAtRow(4);
    }
}
