package ph.txtdis.fx.dialog;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.table.ItemNameTable;
import ph.txtdis.model.Item;
import ph.txtdis.util.Util;

public class FoundItemDialog extends AbstractTableDialog<Item, ItemDTO> {

    public FoundItemDialog(Stage stage, ItemDTO dto) {
        super(Util.getModule(stage), stage, dto);
        showAndWait();
    }

    @Override
    public List<Item> getAddedItems(ItemDTO dto) {
        return table.getItems();
    }

    @Override
    protected TableView<Item> createTable(ItemDTO dto) {
        return new ItemNameTable(this, dto).getTable();
    }

    @Override
    protected TableView<Item> addTableItems(TableView<Item> table, ItemDTO dto) {
        table.setItems((ObservableList<Item>) dto.getList());
        return table;
    }
}
