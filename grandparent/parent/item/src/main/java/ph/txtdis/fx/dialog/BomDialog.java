package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledDisplayField;
import ph.txtdis.fx.input.LabeledIdField;
import ph.txtdis.model.Bom;
import ph.txtdis.model.Item;
import ph.txtdis.type.UomType;
import ph.txtdis.util.Login;

public class BomDialog extends AbstractFieldDialog<Bom, ItemDTO> {

    public BomDialog(Stage stage, ItemDTO dto) {
        super("BOM", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        
        LabeledIdField partField = new LabeledIdField("Part ID");
        LabeledDisplayField nameField = new LabeledDisplayField("Name", 180);
        LabeledComboBox<UomType> uomCombo = new LabeledComboBox<UomType>("UOM", UomType.values());
        LabeledDecimalField qtyField = new LabeledDecimalField("Quantity");
        return Arrays.asList(partField, nameField, uomCombo, qtyField);
    }

    @Override
    protected Bom createEntity(ItemDTO dto, List<InputNode<?>> inputNodes) {
        Item item = dto.get();
        Item part = dto.get(getInputAtRow(0));
        UomType uom = getInputAtRow(2);
        BigDecimal qty = getInputAtRow(3);
        
        Bom bom = new Bom(item, part, uom, qty);
        bom.setCreatedBy(Login.user());
        return bom;
    }
}
