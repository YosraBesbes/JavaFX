package ph.txtdis.fx.table;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.RouteDTO;
import ph.txtdis.fx.dialog.RouteDialog;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.model.Route;

public class RouteTable extends AbstractInputTable<Route, RouteDTO> {

    public RouteTable(Stage stage, RouteDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        TableColumn<Route, Integer> idCol = new IdDisplayColumn<>(stage, "ID No.", "id");
        TableColumn<Route, String> nameCol = new TextDisplayColumn<>(stage, "Name", "name", 120, Pos.CENTER_LEFT);
        TableColumn<Route, String> createdByCol = new TextDisplayColumn<>(stage, "Created by", "createdBy", 120,
                Pos.CENTER_LEFT);
        TableColumn<Route, ZonedDateTime> createdOnCol = new TimestampDisplayColumn<>(stage, "Created on", "timeStamp");
        table.getColumns().addAll(idCol, nameCol, createdByCol, createdOnCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new RouteDialog(stage, dto);
    }

    @Override
    protected List<Route> getAddedItems() {
        List<Route> routes = new ArrayList<>();
        inputDialog.getAddedItems().forEach(route -> {
            dto.set(route);
            dto.save();
            routes.add(dto.get(dto.getId()));
        });
        return routes;
    }

    @Override
    protected ContextMenu createPerTableRowMenu(TableView<Route> table, TableRow<Route> row) {
        return null;
    }
}
