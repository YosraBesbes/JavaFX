package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.QtyDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;

public abstract class AbstractOrderDetailTable<E, D> extends AbstractInputTable<E, D> {

    public AbstractOrderDetailTable(Stage stage, D dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {

        TableColumn<E, Integer> itemIdCol = new IdDisplayColumn<>(stage, "Item No.", "itemId");
        TableColumn<E, String> itemNameCol = new TextDisplayColumn<>(stage, "Name", "item", 180, Pos.CENTER_LEFT);
        TableColumn<E, String> uomCol = new TextDisplayColumn<>(stage, "UOM", "uom", 70, Pos.CENTER);
        TableColumn<E, BigDecimal> qtyCol = new QtyDisplayColumn<>(stage, "Quantity", "qty", 80);
        table.getColumns().addAll(itemIdCol, itemNameCol, uomCol, qtyCol);
    }
}
