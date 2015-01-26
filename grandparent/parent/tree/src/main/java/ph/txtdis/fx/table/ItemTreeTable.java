package ph.txtdis.fx.table;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.ItemTreeDTO;
import ph.txtdis.fx.dialog.ItemTreeDialog;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.model.ItemTree;

public class ItemTreeTable extends AbstractInputTable<ItemTree, ItemTreeDTO> {

    public ItemTreeTable(Stage stage, ItemTreeDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        TableColumn<ItemTree, String> familyCol = new TextDisplayColumn<>(stage, "Family", "family", 180,
                Pos.CENTER_LEFT);
        TableColumn<ItemTree, String> parentCol = new TextDisplayColumn<>(stage, "Parent", "parent", 180,
                Pos.CENTER_LEFT);
        TableColumn<ItemTree, String> createdByCol = new TextDisplayColumn<>(stage, "Created by", "createdBy", 120,
                Pos.CENTER_LEFT);
        TableColumn<ItemTree, ZonedDateTime> createdOnCol = new TimestampDisplayColumn<>(stage, "Created on",
                "createdDate");
        table.getColumns().addAll(familyCol, parentCol, createdByCol, createdOnCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new ItemTreeDialog(stage, dto);
    }

    @Override
    protected List<ItemTree> getAddedItems() {
        List<ItemTree> itemTrees = new ArrayList<>();
        inputDialog.getAddedItems().forEach(itemTree -> {
            dto.set(itemTree);
            dto.save();
            itemTrees.add(dto.get(dto.getId()));
        });
        return itemTrees;
    }

    @Override
    protected ContextMenu createPerTableRowMenu(TableView<ItemTree> table, TableRow<ItemTree> row) {
        return null;
    }
}
