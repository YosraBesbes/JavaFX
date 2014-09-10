package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.PickingDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Picking;

public class ItemNameTable {
    private final TableView<Picking> table;

    @SuppressWarnings("unchecked")
    public ItemNameTable(Stage stage, PickingDTO dto) {
        table = new TableView<>();
        TableColumn<Picking, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Picking, String> nameCol = FX.addDisplayColumn(stage, "Name", "name", 120, dto);
        TableColumn<Picking, String> descriptionCol = FX.addDisplayColumn(stage, "Description", "description", 240,
                dto);
        table.getColumns().addAll(idCol, nameCol, descriptionCol);
    }

    public TableView<Picking> getTable() {
        return table;
    }
}
