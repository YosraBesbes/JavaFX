package ph.txtdis.fx.table;

import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.DecimalDisplayColumn;
import ph.txtdis.model.VolumeByRoute;

public class SummaryTable extends AbstractTable<VolumeByRoute> {

    public SummaryTable(Stage stage, List<String> routes) {
        table.getColumns().add(getColumn(stage, "Total", "totalVol"));
        for (String route : routes)
            table.getColumns().add(getColumn(stage, route, route + "Vol"));
    }

    private DecimalDisplayColumn<VolumeByRoute> getColumn(Stage stage, String name, String field) {
        return new DecimalDisplayColumn<VolumeByRoute>(stage, name, field);
    }
}
