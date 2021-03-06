package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.QtyDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.PickList;

public class PickListTable extends AbstractTable<PickList> {

    @SuppressWarnings("unchecked")
    public PickListTable(Stage stage) {
        TableColumn<PickList, Integer> idCol = new IdDisplayColumn<>(stage, "ID", "itemId");
        TableColumn<PickList, String> nameCol = new TextDisplayColumn<>(stage, "Name", "itemName", 180, Pos.CENTER_LEFT);
        TableColumn<PickList, BigDecimal> qtyCol = new QtyDisplayColumn<>(stage, "Quantity(PC)", "qty", 120);
        table.getColumns().addAll(idCol, nameCol, qtyCol);
        table.setId("Pick List");
    }
}
