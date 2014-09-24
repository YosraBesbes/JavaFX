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
import ph.txtdis.model.Item;
import ph.txtdis.model.Pricing;
import ph.txtdis.type.PricingType;

public class PricingDialog extends AbstractFieldDialog<Pricing, ItemDTO> {

    public PricingDialog(Stage stage, ItemDTO dto) {
        super("Pricing", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {

        LabeledComboBox<PricingType> typeCombo = new LabeledComboBox<PricingType>("Type", PricingType.values());
        LabeledDecimalField priceField = new LabeledDecimalField("Price");
        LabeledDatePicker startPicker = new LabeledDatePicker("Start");
        return Arrays.asList(typeCombo, priceField, startPicker);
    }

    @Override
    protected Pricing createEntity(ItemDTO dto, List<InputNode<?>> inputNodes) {
        Item item = dto.get();
        PricingType type = getInputAtRow(0);
        BigDecimal price = getInputAtRow(1);
        LocalDate start = getInputAtRow(2);

        return new Pricing(item, type, price, start);
    }
}
