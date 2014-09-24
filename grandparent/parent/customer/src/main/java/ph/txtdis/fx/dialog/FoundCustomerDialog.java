package ph.txtdis.fx.dialog;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.fx.table.CustomerNameTable;
import ph.txtdis.model.Customer;
import ph.txtdis.util.Util;

public class FoundCustomerDialog extends AbstractTableDialog<Customer, CustomerDTO> {

    public FoundCustomerDialog(Stage stage, CustomerDTO dto) {
        super(Util.getModule(stage), stage, dto);
        showAndWait();
    }

    @Override
    protected TableView<Customer> createTable(CustomerDTO dto) {
        return new CustomerNameTable(this, dto).getTable();
    }

    @Override
    protected TableView<Customer> addTableItems(TableView<Customer> table, CustomerDTO dto) {
        table.setItems((ObservableList<Customer>) dto.getList());
        return table;
    }

    @Override
    public List<Customer> getAddedItems(CustomerDTO dto) {
        return table.getItems();
    }
}
