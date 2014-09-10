package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.RemittanceDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Remittance;

public class ItemNameTable {
    private final TableView<Remittance> table;

    @SuppressWarnings("unchecked")
    public ItemNameTable(Stage stage, RemittanceDTO dto) {
        table = new TableView<>();
        TableColumn<Remittance, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Remittance, String> nameCol = FX.addDisplayColumn(stage, "Name", "name", 120, dto);
        TableColumn<Remittance, String> descriptionCol = FX.addDisplayColumn(stage, "Description", "description", 240,
                dto);
        table.getColumns().addAll(idCol, nameCol, descriptionCol);
    }

    public TableView<Remittance> getTable() {
        return table;
    }
}
