package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Customer;

public class CustomerNameTable {
    private final TableView<Customer> table;

    @SuppressWarnings("unchecked")
    public CustomerNameTable(Stage stage, CustomerDTO dto) {
        table = new TableView<>();
        TableColumn<Customer, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Customer, String> nameCol = FX.addDisplayColumn(stage, "Name", "name", 240, dto);
        table.getColumns().addAll(idCol, nameCol);
    }

    public TableView<Customer> getTable() {
        return table;
    }
}
