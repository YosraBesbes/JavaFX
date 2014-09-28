package ph.txtdis.fx.dialog;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.util.FX;

public abstract class AbstractFieldDialog<E, D> extends AbstractInputDialog<D> implements Inputted<E> {
    private List<E> addedItems = new ArrayList<E>();
    protected List<InputNode<?>> inputNodes;

    public AbstractFieldDialog(String name, Stage stage, D dto) {
        super(name + " Data Entry", stage, dto);
        inputNodes.get(0).requestFocus();
    }

    @Override
    protected void populateGrid(GridPane gridPane, D dto) {
        inputNodes = addNodes();
        if (inputNodes != null)
            addNodes(gridPane);
    }

    private void addNodes(GridPane gridPane) {
        for (int i = 0; i < inputNodes.size(); i++) {
            List<Node> nodes = inputNodes.get(i).getNodes();
            for (int j = 0; j < nodes.size(); j++)
                gridPane.add(nodes.get(j), j, i);
        }
    }

    protected void addItems(D dto, List<InputNode<?>> inputNodes) {
        addedItems.add(createEntity(dto, inputNodes));
        resetNodes(inputNodes);
    }

    protected void resetNodes(List<InputNode<?>> inputNodes) {
        inputNodes.forEach(inputNode -> inputNode.reset());
        inputNodes.get(0).requestFocus();
    }

    @Override
    protected Button[] createButtons(D dto) {
        Button addButton = createAddButton(dto);
        Button closeButton = createCloseButton();
        return new Button[] { addButton, closeButton };
    }

    private Button createAddButton(D dto) {
        Button addButton = FX.createLargeButton("Add");
        addButton.setOnAction(event -> addItems(dto, inputNodes));
        addBinding(addButton);
        return addButton;
    }

    private void addBinding(Button addButton) {
        addButton.disableProperty().bind(getAddButtonBindings());
    }

    private BooleanBinding getAddButtonBindings() {
        BooleanBinding binding = inputNodes.get(0).isEmpty();
        for (int i = 1; i < inputNodes.size(); i++)
            binding = binding.or(inputNodes.get(i).isEmpty());
        return binding;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getInputAtRow(int index) {
        return (T) inputNodes.get(index).getValue();
    }

    protected abstract List<InputNode<?>> addNodes();

    protected abstract E createEntity(D dto, List<InputNode<?>> inputNodes);

    @Override
    public List<E> getAddedItems() {
        return addedItems;
    }
}
