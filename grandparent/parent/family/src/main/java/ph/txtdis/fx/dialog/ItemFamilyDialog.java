package ph.txtdis.fx.dialog;

import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.ItemFamilyDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.type.ItemTier;

public class ItemFamilyDialog extends AbstractFieldDialog<ItemFamily, ItemFamilyDTO> {

    private LabeledComboBox<ItemTier> tierCombo;
    private LabeledStringField familyField;
    private ItemFamilyDTO dto;

    public ItemFamilyDialog(Stage stage, ItemFamilyDTO dto) {
        super("ItemFamily", stage, dto);
        addFamilyFieldListener();
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        this.dto = object;
        createInputNodes();
        return Arrays.asList(familyField);
    }

    private void createInputNodes() {
        familyField = new LabeledStringField("Name");
        tierCombo = new LabeledComboBox<>("Tier", ItemTier.values());
    }

    private void addFamilyFieldListener() {
        familyField.getTextField().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                disallowDuplicates();
        });
    }

    private void disallowDuplicates() {
        String name = familyField.getValue();
        ItemFamily itemFamily = dto.get(name);
        if (itemFamily != null)
            handleDuplicateError(itemFamily + "'s already in database");
    }

    private void handleDuplicateError(String error) {
        new ErrorDialog(this, error);
        resetNodes(inputNodes);
        familyField.getTextField().requestFocus();
    }

    @Override
    protected ItemFamily createEntity(ItemFamilyDTO dto, List<InputNode<?>> inputNodes) {
        return new ItemFamily(familyField.getValue(), tierCombo.getValue());
    }

    @Override
    protected void addItems(ItemFamilyDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}
