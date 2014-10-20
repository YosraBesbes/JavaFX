package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.DecimalDisplayColumn;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.VolumeSummary;

public class PerItemTable extends AbstractTable<VolumeSummary> {

    @SuppressWarnings("unchecked")
    public PerItemTable(Stage stage) {
        TableColumn<VolumeSummary, Integer> idCol = new IdDisplayColumn<>(stage, "Item No.", "itemId");
        TableColumn<VolumeSummary, String> itemCol = new TextDisplayColumn<>(stage, "Name", "item", 180,
                Pos.CENTER_LEFT);
        TableColumn<VolumeSummary, BigDecimal> caseCol = new DecimalDisplayColumn<>(stage, "Qty(CS)", "vol");
        table.getColumns().addAll(idCol, itemCol, caseCol);
    }
}
