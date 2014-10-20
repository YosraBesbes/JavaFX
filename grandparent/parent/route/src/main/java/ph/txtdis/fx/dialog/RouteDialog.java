package ph.txtdis.fx.dialog;

import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.RouteDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Route;

public class RouteDialog extends AbstractFieldDialog<Route, RouteDTO> {

    private LabeledStringField routeField;
    private RouteDTO dto;

    public RouteDialog(Stage stage, RouteDTO dto) {
        super("Route", stage, dto);
        addRouteFieldListener();
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        this.dto = object;
        createInputNodes();
        return Arrays.asList(routeField);
    }

    private void createInputNodes() {
        routeField = new LabeledStringField("Name");
    }

    private void addRouteFieldListener() {
        routeField.getTextField().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                disallowDuplicates();
        });
    }

    private void disallowDuplicates() {
        String name = routeField.getValue();
        Route route = dto.get(name);
        if (route != null)
            handleDuplicateError(route + "'s already in database");
    }

    private void handleDuplicateError(String error) {
        new ErrorDialog(this, error);
        resetNodes(inputNodes);
        routeField.getTextField().requestFocus();
    }

    @Override
    protected Route createEntity(RouteDTO dto, List<InputNode<?>> inputNodes) {
        return new Route(routeField.getValue());
    }

    @Override
    protected void addItems(RouteDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}
