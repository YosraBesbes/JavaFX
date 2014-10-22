package ph.txtdis.fx.table;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.Item;

public class ItemTable extends AbstractTable<Item> {

    @SuppressWarnings("unchecked")
    public ItemTable(Stage stage) {
        TableColumn<Item, Integer> idCol = new IdDisplayColumn<>(stage, "ID No.", "id");
        TableColumn<Item, String> nameCol = new TextDisplayColumn<>(stage, "Name", "name", 180, Pos.CENTER_LEFT);
        TableColumn<Item, String> descriptionCol = new TextDisplayColumn<>(stage, "Description", "description", 540,
                Pos.CENTER_LEFT);
        table.getColumns().addAll(idCol, nameCol, descriptionCol);
    }
}
