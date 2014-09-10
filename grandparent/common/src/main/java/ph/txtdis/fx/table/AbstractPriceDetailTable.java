package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;
import ph.txtdis.app.OrderApp;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Priced;

public abstract class AbstractPriceDetailTable<E, D> extends AbstractOrderDetailTable<E, D> {

    public AbstractPriceDetailTable(Stage stage, D dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        super.addTableColumns();
        TableColumn<E, BigDecimal> priceCol = FX.addPriceColumn("Price", "price");
        TableColumn<E, BigDecimal> subtotalCol = FX.addPriceColumn("Subtotal", "subtotal");
        table.getColumns().addAll(priceCol, subtotalCol);
    }

    @Override
    protected void addTableItems() {
        super.addTableItems();
        refreshTableandTotals();
    }

    @Override
    protected void removeTableItem(TableRow<E> row) {
        super.removeTableItem(row);
        refreshTableandTotals();
    }

    @SuppressWarnings("unchecked")
    private void refreshTableandTotals() {
        ((OrderApp<?>) stage).iterateDetailTableItems((List<Priced>) table.getItems());
    }
}
