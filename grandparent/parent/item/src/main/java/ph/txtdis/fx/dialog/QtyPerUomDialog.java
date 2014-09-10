package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledCheckBox;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.model.Item;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.type.UomType;
import ph.txtdis.util.Login;

public class QtyPerUomDialog extends AbstractFieldDialog<QtyPerUom, ItemDTO> {

    public QtyPerUomDialog(Stage stage, ItemDTO dto) {
        super("Quantity per UOM", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        
        LabeledComboBox<UomType> uomCombo = new LabeledComboBox<>("UOM", UomType.values());
        LabeledDecimalField qtyField = new LabeledDecimalField("Quantity");
        LabeledCheckBox isPurchasedCheckBox = new LabeledCheckBox("Purchased");
        LabeledCheckBox isSoldCheckBox = new LabeledCheckBox("Sold");
        LabeledCheckBox isReportedCheckBox = new LabeledCheckBox("Reported");
        return Arrays.asList(uomCombo, qtyField, isPurchasedCheckBox, isSoldCheckBox, isReportedCheckBox);
    }

    @Override
    protected QtyPerUom createEntity(ItemDTO dto, List<InputNode<?>> inputNodes) {
        Item item = dto.get();
        UomType uom = getInputAtRow(0);
        BigDecimal qty = getInputAtRow(1);
        boolean isPurchased = getInputAtRow(2);
        boolean isSold = getInputAtRow(3);
        boolean isReported = getInputAtRow(4);
        QtyPerUom qtyPerUom = new QtyPerUom(item, uom, qty, isPurchased, isSold, isReported);
        qtyPerUom.setCreatedBy(Login.user());
        return qtyPerUom;
    }
}
