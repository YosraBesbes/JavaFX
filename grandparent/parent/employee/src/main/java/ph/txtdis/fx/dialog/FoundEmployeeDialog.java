package ph.txtdis.fx.dialog;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.table.EmployeeNameTable;
import ph.txtdis.model.Employee;
import ph.txtdis.util.Util;

public class FoundEmployeeDialog extends AbstractTableDialog<Employee, EmployeeDTO> {

    public FoundEmployeeDialog(Stage stage, EmployeeDTO dto) {
        super(Util.getModule(stage), stage, dto);
        showAndWait();
    }

    @Override
    public List<Employee> getAddedItems(EmployeeDTO dto) {
        return table.getItems();
    }

    @Override
    protected TableView<Employee> createTable(EmployeeDTO dto) {
        return new EmployeeNameTable(this, dto).getTable();
    }

    @Override
    protected TableView<Employee> addTableItems(TableView<Employee> table, EmployeeDTO dto) {
        table.setItems((ObservableList<Employee>) dto.getList());
        return table;
    }
}
