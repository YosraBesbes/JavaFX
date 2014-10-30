package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.CurrencyDisplayColumn;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.ItemPrice;

public class ItemTable extends AbstractTable<ItemPrice> {

    @SuppressWarnings("unchecked")
    public ItemTable(Stage stage) {
        TableColumn<ItemPrice, Integer> idCol = new IdDisplayColumn<>(stage, "ID No.", "id");
        TableColumn<ItemPrice, String> nameCol = new TextDisplayColumn<>(stage, "Name", "name", 180, Pos.CENTER_LEFT);
        TableColumn<ItemPrice, String> descriptionCol = new TextDisplayColumn<>(stage, "Description", "description",
                540, Pos.CENTER_LEFT);
        TableColumn<ItemPrice, BigDecimal> priceCol = new CurrencyDisplayColumn<>(stage, "Price", "priceValue");
        table.getColumns().addAll(idCol, nameCol, descriptionCol, priceCol);
    }
}
