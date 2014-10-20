package ph.txtdis.fx.dialog;

import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.TruckDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Truck;

public class TruckDialog extends AbstractFieldDialog<Truck, TruckDTO> {

    private LabeledStringField plateNoField;
    private TruckDTO dto;

    public TruckDialog(Stage stage, TruckDTO dto) {
        super("Truck", stage, dto);
        addPlateNoFieldListener();
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        this.dto = object;
        createInputNodes();
        return Arrays.asList(plateNoField);
    }

    private void createInputNodes() {
        plateNoField = new LabeledStringField("Plate No.");
    }

    private void addPlateNoFieldListener() {
        plateNoField.getTextField().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                validateStartingNo();
        });
    }

    private void validateStartingNo() {
        String plateNo = plateNoField.getValue();
        Truck truck = dto.get(plateNo);
        if (truck != null)
            handleDuplicateError(truck + "'s already in database");
    }

    private void handleDuplicateError(String error) {
        new ErrorDialog(this, error);
        resetNodes(inputNodes);
        plateNoField.getTextField().requestFocus();
    }

    @Override
    protected Truck createEntity(TruckDTO dto, List<InputNode<?>> inputNodes) {
        return new Truck(plateNoField.getValue());
    }

    @Override
    protected void addItems(TruckDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}
