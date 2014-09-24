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
import ph.txtdis.exception.NotFoundException;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledIdNameField;
import ph.txtdis.fx.input.LabeledIntegerDisplay;
import ph.txtdis.model.Item;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
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

        itemField = new LabeledIdNameField("Item ID No.", 18);
        daysLevelOldDisplay = new LabeledIntegerDisplay("Days' Level Now");
        uomCombo = new LabeledComboBox<>("UOM", UomType.values());
        qtyField = new LabeledDecimalField("Quantity");
        daysLevelNewDisplay = new LabeledIntegerDisplay("New Days' Level");

        return Arrays.asList(itemField, daysLevelOldDisplay, uomCombo, qtyField, daysLevelNewDisplay);
    }

    private void setListeners() {
        itemDTO = App.getContext().getBean(ItemDTO.class);
        itemField.getIdField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB) {
                int id = itemField.getValue();
                if (itemDTO.exists(id)) {
                    actWhenFound(id);
                } else {
                    try {
                        throw new NotFoundException("Item ID No. " + id);
                    } catch (Exception e) {
                        actOnError(this, e);
                    }
                }
            }
        });

        qtyField.getDecimalField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                showDaysLevel(qtyField.getValue());
        });

    }

    private void actWhenFound(int id) {
        itemDTO.setById(id);
        uomCombo.setItems(itemDTO.getPurchasingUoms());
        itemField.getNameField().setText(itemDTO.getName());
        showDaysLevel();
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

    private void actOnError(Stage stage, Exception e) {
        new ErrorDialog(stage, e.getMessage());
        inputNodes.forEach(inputNode -> inputNode.reset());
    }

    @Override
    protected PurchasingDetail createEntity(PurchasingDTO dto, List<InputNode<?>> inputNodes) {

        @SuppressWarnings("rawtypes")
        LocalDate date = ((OrderApp) stage).getPickerDate();
        Purchasing purchasing = dto.get();
        Item item = itemDTO.get();
        int daysLevelBefore = getInputAtRow(1);
        UomType uom = getInputAtRow(2);
        BigDecimal qty = getInputAtRow(3);
        int daysLevelAfter = getInputAtRow(4);

        PurchasingDetail detail = new PurchasingDetail(purchasing, item, uom, qty);
        detail.setPrice(itemDTO.getLatestPurchasePrice(date));
        detail.setSubtotal(detail.getPrice().multiply(qty.multiply(itemDTO.getQtyPerUomMap().get(uom))));
        detail.setDaysLevelBefore(daysLevelBefore);
        detail.setDaysLevelAfter(daysLevelAfter);
        return detail;
    }
}
