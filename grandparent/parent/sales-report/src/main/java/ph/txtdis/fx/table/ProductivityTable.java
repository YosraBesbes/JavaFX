package ph.txtdis.fx.table;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.IntegerDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.ProductivityPerFamilyByRoute;

public class ProductivityTable extends AbstractTable<ProductivityPerFamilyByRoute> {

    public ProductivityTable(Stage stage, List<String> routes) {
        TableColumn<ProductivityPerFamilyByRoute, String> categoryCol = new TextDisplayColumn<>(stage, "Category",
                "family", 180, Pos.CENTER_LEFT);
        table.getColumns().add(categoryCol);
        for (String route : routes) {
            TableColumn<ProductivityPerFamilyByRoute, Integer> valueCol = new IntegerDisplayColumn<>(stage, route,
                    route + "Count");
            table.getColumns().add(valueCol);
        }
    }
}
