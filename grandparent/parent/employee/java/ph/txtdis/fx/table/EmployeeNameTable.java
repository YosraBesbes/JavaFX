package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Employee;

public class EmployeeNameTable {
    private final TableView<Employee> table;

    @SuppressWarnings("unchecked")
    public EmployeeNameTable(Stage stage, EmployeeDTO dto) {
        table = new TableView<>();
        TableColumn<Employee, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Employee, String> surnameCol = FX.addDisplayColumn(stage, "Surname", "surname", 180, dto);
        TableColumn<Employee, String> nameCol = FX.addDisplayColumn(stage, "Given Name/s", "name", 240, dto);
        table.getColumns().addAll(idCol, surnameCol, nameCol);
    }

    public TableView<Employee> getTable() {
        return table;
    }
}
