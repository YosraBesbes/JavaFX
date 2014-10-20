package ph.txtdis.fx.dialog;

import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.WarehouseDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Warehouse;

public class WarehouseDialog extends AbstractFieldDialog<Warehouse, WarehouseDTO> {

    private LabeledStringField warehouseField;
    private WarehouseDTO dto;

    public WarehouseDialog(Stage stage, WarehouseDTO dto) {
        super("Warehouse", stage, dto);
        addWarehouseFieldListener();
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        this.dto = object;
        createInputNodes();
        return Arrays.asList(warehouseField);
    }

    private void createInputNodes() {
        warehouseField = new LabeledStringField("Name");
    }

    private void addWarehouseFieldListener() {
        warehouseField.getTextField().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                disallowDuplicates();
        });
    }

    private void disallowDuplicates() {
        String name = warehouseField.getValue();
        Warehouse warehouse = dto.get(name);
        if (warehouse != null)
            handleDuplicateError(warehouse + "'s already in database");
    }

    private void handleDuplicateError(String error) {
        new ErrorDialog(this, error);
        resetNodes(inputNodes);
        warehouseField.getTextField().requestFocus();
    }

    @Override
    protected Warehouse createEntity(WarehouseDTO dto, List<InputNode<?>> inputNodes) {
        return new Warehouse(warehouseField.getValue());
    }

    @Override
    protected void addItems(WarehouseDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}
