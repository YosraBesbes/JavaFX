package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.DecimalDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.VolumePerChannelByRoute;

public class PerChannelTable extends AbstractTable<VolumePerChannelByRoute> {

    public PerChannelTable(Stage stage, List<String> routes) {
        TableColumn<VolumePerChannelByRoute, String> channelCol = new TextDisplayColumn<>(stage, "Channel", "channel",
                180, Pos.CENTER_LEFT);
        table.getColumns().add(channelCol);
        for (String route : routes) {
            TableColumn<VolumePerChannelByRoute, BigDecimal> valueCol = new DecimalDisplayColumn<>(stage, route, route
                    + "Vol");
            table.getColumns().add(valueCol);
        }
    }
}
