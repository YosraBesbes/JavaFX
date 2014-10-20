package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.DecimalDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.VolumePerTownByRoute;

public class PerTownTable extends AbstractTable<VolumePerTownByRoute> {

    public PerTownTable(Stage stage, List<String> routes) {
        TableColumn<VolumePerTownByRoute, String> townCol = new TextDisplayColumn<>(stage, "Town", "town", 180,
                Pos.CENTER_LEFT);
        table.getColumns().add(townCol);
        for (String route : routes) {
            TableColumn<VolumePerTownByRoute, BigDecimal> valueCol = new DecimalDisplayColumn<>(stage, route, route
                    + "Vol");
            table.getColumns().add(valueCol);
        }
    }
}
