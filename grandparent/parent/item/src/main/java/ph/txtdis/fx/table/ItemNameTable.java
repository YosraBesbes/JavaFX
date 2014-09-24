package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Item;

public class ItemNameTable {
    private final TableView<Item> table;

    @SuppressWarnings("unchecked")
    public ItemNameTable(Stage stage, ItemDTO dto) {
        table = new TableView<>();
        TableColumn<Item, Long> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Item, String> nameCol = FX.addDisplayColumn(stage, "Name", "name", 120, dto);
        TableColumn<Item, String> descriptionCol = FX.addDisplayColumn(stage, "Description", "description", 240, dto);
        table.getColumns().addAll(idCol, nameCol, descriptionCol);
    }

    public TableView<Item> getTable() {
        return table;
    }
}
