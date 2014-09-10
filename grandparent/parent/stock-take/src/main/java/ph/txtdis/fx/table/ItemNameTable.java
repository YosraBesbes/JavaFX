package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.StockTakeDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.StockTake;

public class ItemNameTable {
    private final TableView<StockTake> table;

    @SuppressWarnings("unchecked")
    public ItemNameTable(Stage stage, StockTakeDTO dto) {
        table = new TableView<>();
        TableColumn<StockTake, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<StockTake, String> nameCol = FX.addDisplayColumn(stage, "Name", "name", 120, dto);
        TableColumn<StockTake, String> descriptionCol = FX.addDisplayColumn(stage, "Description", "description", 240,
                dto);
        table.getColumns().addAll(idCol, nameCol, descriptionCol);
    }

    public TableView<StockTake> getTable() {
        return table;
    }
}
