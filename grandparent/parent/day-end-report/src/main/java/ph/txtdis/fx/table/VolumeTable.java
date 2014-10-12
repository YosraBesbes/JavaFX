package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.QtyDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.VolumeSummary;

public class VolumeTable extends AbstractTable<VolumeSummary> {

    @SuppressWarnings("unchecked")
    public VolumeTable(Stage stage) {
        TableColumn<VolumeSummary, Integer> itemIdCol = new IdDisplayColumn<>(stage, "Item No.", "itemId");
        TableColumn<VolumeSummary, String> itemNameCol = new TextDisplayColumn<>(stage, "Name", "item", 180,
                Pos.CENTER_LEFT);
        TableColumn<VolumeSummary, BigDecimal> qtyCol = new QtyDisplayColumn<>(stage, "Quantity", "qty", 80);
        table.getColumns().addAll(itemIdCol, itemNameCol, qtyCol);
        table.setId("Volume");
    }
}
