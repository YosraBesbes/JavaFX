package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Customer;

public class CustomerNameTable extends AbstractTable<Customer> {

    @SuppressWarnings("unchecked")
    public CustomerNameTable(Stage stage, CustomerDTO dto) {
        TableColumn<Customer, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Customer, String> nameCol = FX.addDisplayColumn(stage, "Name", "name", 240, dto);
        table.getColumns().addAll(idCol, nameCol);
    }
}
