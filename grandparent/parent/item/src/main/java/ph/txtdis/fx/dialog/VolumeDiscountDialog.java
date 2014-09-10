package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledIntegerField;
import ph.txtdis.model.Item;
import ph.txtdis.model.VolumeDiscount;
import ph.txtdis.type.UomType;
import ph.txtdis.type.VolumeDiscountType;
import ph.txtdis.util.Login;

public class VolumeDiscountDialog extends AbstractFieldDialog<VolumeDiscount, ItemDTO> {

    public VolumeDiscountDialog(Stage stage, ItemDTO dto) {
        super("VolumeDiscount", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {

        LabeledComboBox<VolumeDiscountType> typeCombo = new LabeledComboBox<VolumeDiscountType>("Type",
                VolumeDiscountType.values());
        LabeledComboBox<UomType> uomCombo = new LabeledComboBox<UomType>("UOM", UomType.values());
        LabeledIntegerField cutOffField = new LabeledIntegerField("Target");
        LabeledDecimalField discountField = new LabeledDecimalField("Discount");
        LabeledDatePicker startPicker = new LabeledDatePicker("Start");
        return Arrays.asList(typeCombo, uomCombo, cutOffField, discountField, startPicker);
    }

    @Override
    protected VolumeDiscount createEntity(ItemDTO dto, List<InputNode<?>> inputNodes) {
        Item item = dto.get();
        VolumeDiscountType type = getInputAtRow(0);
        UomType uom = getInputAtRow(1);
        int cutOff = getInputAtRow(2);
        BigDecimal discount = getInputAtRow(3);
        LocalDate start = getInputAtRow(4);

        VolumeDiscount volumeDiscount = new VolumeDiscount(item, type, uom, cutOff, discount, start);
        volumeDiscount.setCreatedBy(Login.user());
        return volumeDiscount;
    }

    @Override
    protected void addItems(ItemDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}
