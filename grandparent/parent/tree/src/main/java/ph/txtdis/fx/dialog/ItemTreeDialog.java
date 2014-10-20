package ph.txtdis.fx.dialog;

import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.ItemFamilyDTO;
import ph.txtdis.dto.ItemTreeDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.model.ItemTree;

public class ItemTreeDialog extends AbstractFieldDialog<ItemTree, ItemTreeDTO> {

    private LabeledComboBox<ItemFamily> familyCombo;
    private LabeledComboBox<ItemFamily> parentCombo;
    private ItemFamilyDTO family;

    public ItemTreeDialog(Stage stage, ItemTreeDTO dto) {
        super("Item Family Tree", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        createInputNodes();
        return Arrays.asList(familyCombo, parentCombo);
    }

    private void createInputNodes() {
        family = App.getContext().getBean(ItemFamilyDTO.class);
        familyCombo = new LabeledComboBox<>("Family", family.list());
        parentCombo = new LabeledComboBox<>("Parent", family.list());
    }

    @Override
    protected ItemTree createEntity(ItemTreeDTO dto, List<InputNode<?>> inputNodes) {
        return new ItemTree(familyCombo.getValue(), parentCombo.getValue());
    }

    @Override
    protected void addItems(ItemTreeDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}
