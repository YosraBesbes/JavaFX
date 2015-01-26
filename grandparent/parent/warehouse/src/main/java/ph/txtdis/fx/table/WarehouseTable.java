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
import ph.txtdis.dto.WarehouseDTO;
import ph.txtdis.fx.dialog.WarehouseDialog;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.model.Warehouse;

public class WarehouseTable extends AbstractInputTable<Warehouse, WarehouseDTO> {

    public WarehouseTable(Stage stage, WarehouseDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        TableColumn<Warehouse, Integer> idCol = new IdDisplayColumn<>(stage, "ID No.", "id");
        TableColumn<Warehouse, String> nameCol = new TextDisplayColumn<>(stage, "Name", "name", 120, Pos.CENTER_LEFT);
        TableColumn<Warehouse, String> createdByCol = new TextDisplayColumn<>(stage, "Created by", "createdBy", 120,
                Pos.CENTER_LEFT);
        TableColumn<Warehouse, ZonedDateTime> createdOnCol = new TimestampDisplayColumn<>(stage, "Created on",
                "createdDate");
        table.getColumns().addAll(idCol, nameCol, createdByCol, createdOnCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new WarehouseDialog(stage, dto);
    }

    @Override
    protected List<Warehouse> getAddedItems() {
        List<Warehouse> warehouses = new ArrayList<>();
        inputDialog.getAddedItems().forEach(warehouse -> {
            dto.set(warehouse);
            dto.save();
            warehouses.add(dto.get(dto.getId()));
        });
        return warehouses;
    }

    @Override
    protected ContextMenu createPerTableRowMenu(TableView<Warehouse> table, TableRow<Warehouse> row) {
        return null;
    }
}
