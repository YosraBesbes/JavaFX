package ph.txtdis.fx.table;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.Customer;

public class CustomerTable extends AbstractTable<Customer> {

    @SuppressWarnings("unchecked")
    public CustomerTable(Stage stage) {
        TableColumn<Customer, Integer> idCol = new IdDisplayColumn<>(stage, "ID No.", "id");
        TableColumn<Customer, String> nameCol = new TextDisplayColumn<>(stage, "Name", "name", 360, Pos.CENTER_LEFT);
        TableColumn<Customer, String> streetCol = new TextDisplayColumn<>(stage, "Street / Stall", "street", 360,
                Pos.CENTER_LEFT);
        TableColumn<Customer, String> barangayCol = new TextDisplayColumn<>(stage, "Barangay", "barangay", 180,
                Pos.CENTER_LEFT);
        TableColumn<Customer, String> cityCol = new TextDisplayColumn<>(stage, "City", "city", 180, Pos.CENTER_LEFT);
        TableColumn<Customer, String> provinceCol = new TextDisplayColumn<>(stage, "Province", "province", 180,
                Pos.CENTER_LEFT);
        table.getColumns().addAll(idCol, nameCol, streetCol, barangayCol, cityCol, provinceCol);
    }
}
