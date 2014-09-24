package ph.txtdis.fx.dialog;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.RouteDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.model.Customer;
import ph.txtdis.model.Route;
import ph.txtdis.model.Routing;

public class RoutingDialog extends AbstractFieldDialog<Routing, CustomerDTO> {

    public RoutingDialog(Stage stage, CustomerDTO dto) {
        super("Routing", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        RouteDTO route = App.getContext().getBean(RouteDTO.class);
        LabeledComboBox<Route> routeCombo = new LabeledComboBox<>("Route", route.list());
        LabeledDatePicker startPicker = new LabeledDatePicker("Start");
        return Arrays.asList(routeCombo, startPicker);
    }

    @Override
    protected Routing createEntity(CustomerDTO dto, List<InputNode<?>> inputNodes) {
        Customer customer = dto.get();
        Route route = getInputAtRow(0);
        LocalDate start = getInputAtRow(1);

        return new Routing(customer, route, start);
    }

    @Override
    protected void addItems(CustomerDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}
