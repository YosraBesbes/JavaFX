package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.DisplayTableColumn;
import ph.txtdis.fx.tablecolumn.IdDisplayTableColumn;
import ph.txtdis.fx.tablecolumn.QtyDisplayTableColumn;
import ph.txtdis.model.PickList;

public class PickListTable extends AbstractTable<PickList> {

    @SuppressWarnings("unchecked")
    public PickListTable(Stage stage) {
        TableColumn<PickList, Integer> idCol = new IdDisplayTableColumn<PickList>(stage, "ID", "itemId", 50);
        TableColumn<PickList, String> nameCol = new DisplayTableColumn<PickList, String>(stage, "Name", "itemName", 180);
        TableColumn<PickList, BigDecimal> qtyCol = new QtyDisplayTableColumn<PickList>(stage, "Quantity(PC)", "qty",
                120);
        table.getColumns().addAll(idCol, nameCol, qtyCol);
    }
}
