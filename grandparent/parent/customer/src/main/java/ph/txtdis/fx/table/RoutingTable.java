package ph.txtdis.fx.table;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.RouteDTO;
import ph.txtdis.fx.dialog.RoutingDialog;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Route;
import ph.txtdis.model.Routing;
import ph.txtdis.model.Users;

public class RoutingTable extends AbstractInputTable<Routing, CustomerDTO> {

    public RoutingTable(Stage stage, CustomerDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {

        RouteDTO route = App.getContext().getBean(RouteDTO.class);

        TableColumn<Routing, Route> routeCol = FX.addComboColumn("Route", "route", route.list());
        TableColumn<Routing, LocalDate> startCol = FX.addDateColumn("Start", "startDate");
        TableColumn<Routing, Users> assignedByCol = FX.createColumn("Assigned By", "createdBy", 120);
        TableColumn<Routing, ZonedDateTime> assignedDateCol = new TimestampDisplayColumn<>(stage, "Assigned On",
                "createdDate");
        table.getColumns().addAll(routeCol, startCol, assignedByCol, assignedDateCol);
        table.setMinHeight(65);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new RoutingDialog(stage, dto);
    }
}
