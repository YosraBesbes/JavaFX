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
import ph.txtdis.dto.ItemFamilyDTO;
import ph.txtdis.fx.dialog.ItemFamilyDialog;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.model.ItemFamily;

public class ItemFamilyTable extends AbstractInputTable<ItemFamily, ItemFamilyDTO> {

    public ItemFamilyTable(Stage stage, ItemFamilyDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        TableColumn<ItemFamily, Integer> idCol = new IdDisplayColumn<>(stage, "ID No.", "id");
        TableColumn<ItemFamily, String> nameCol = new TextDisplayColumn<>(stage, "Name", "name", 120, Pos.CENTER_LEFT);
        TableColumn<ItemFamily, String> createdByCol = new TextDisplayColumn<>(stage, "Created by", "createdBy", 120,
                Pos.CENTER_LEFT);
        TableColumn<ItemFamily, ZonedDateTime> createdOnCol = new TimestampDisplayColumn<>(stage, "Created on",
                "timeStamp");
        table.getColumns().addAll(idCol, nameCol, createdByCol, createdOnCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new ItemFamilyDialog(stage, dto);
    }

    @Override
    protected List<ItemFamily> getAddedItems() {
        List<ItemFamily> itemFamilys = new ArrayList<>();
        inputDialog.getAddedItems().forEach(itemFamily -> {
            dto.set(itemFamily);
            dto.save();
            itemFamilys.add(dto.get(dto.getId()));
        });
        return itemFamilys;
    }

    @Override
    protected ContextMenu createPerTableRowMenu(TableView<ItemFamily> table, TableRow<ItemFamily> row) {
        return null;
    }
}
