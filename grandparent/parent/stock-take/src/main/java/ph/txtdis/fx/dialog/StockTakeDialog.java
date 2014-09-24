package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.dto.StockTakeDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledIdNameField;
import ph.txtdis.model.Item;
import ph.txtdis.model.Quality;
import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.type.UomType;

public class StockTakeDialog extends AbstractFieldDialog<StockTakeDetail, StockTakeDTO> {

    private LabeledIdNameField itemField;
    private ItemDTO itemDTO;

    public StockTakeDialog(Stage stage, StockTakeDTO dto) {
        super("Stock Take", stage, dto);
        setListeners();
    }

    private void setListeners() {
        itemDTO = App.getContext().getBean(ItemDTO.class);
        itemField.getIdField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                handleId(itemField.getValue());
        });
    }

    private void handleId(int id) {
        if (itemDTO.exists(id))
            handleFound(id);
        else
            handleError(this, id);
    }

    private void handleFound(int id) {
        itemDTO.setById(id);
        itemField.getNameField().setText(itemDTO.getName());
    }

    private void handleError(Stage stage, int id) {
        new ErrorDialog(stage, "Item ID No. " + id + "\nis not in this database");
        inputNodes.forEach(inputNode -> inputNode.reset());
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        QualityRated qualityDTO = App.getContext().getBean(QualityRated.class);

        itemField = new LabeledIdNameField("Item ID", 180);
        LabeledComboBox<UomType> uomCombo = new LabeledComboBox<>("UOM", UomType.values());
        LabeledDecimalField qtyField = new LabeledDecimalField("Quantity");
        LabeledComboBox<Quality> qualityCombo = new LabeledComboBox<>("Quality", qualityDTO.list());

        return Arrays.asList(itemField, uomCombo, qtyField, qualityCombo);

    }

    @Override
    protected StockTakeDetail createEntity(StockTakeDTO dto, List<InputNode<?>> inputNodes) {

        StockTake stockTake = dto.get();
        Item item = itemDTO.get();
        UomType uom = getInputAtRow(1);
        BigDecimal qty = getInputAtRow(2);
        Quality quality = getInputAtRow(3);

        return new StockTakeDetail(stockTake, item, uom, qty, quality);
    }
}
