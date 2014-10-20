package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;
import ph.txtdis.app.OrderApp;
import ph.txtdis.fx.tablecolumn.CurrencyDisplayColumn;
import ph.txtdis.model.Priced;

public abstract class AbstractPriceTable<E, D> extends AbstractQualityDetailTable<E, D> {

    public AbstractPriceTable(Stage stage, D dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        super.addTableColumns();
        TableColumn<E, BigDecimal> priceCol = new CurrencyDisplayColumn<>(stage, "Price", "price");
        TableColumn<E, BigDecimal> subtotalCol = new CurrencyDisplayColumn<>(stage, "Subtotal", "subtotal");
        table.getColumns().addAll(priceCol, subtotalCol);
    }

    @Override
    protected void addTableItems() {
        super.addTableItems();
        refreshTableandTotals();
    }

    @Override
    protected void handleRowMenuItemSelection(TableRow<E> row) {
        super.handleRowMenuItemSelection(row);
        refreshTableandTotals();
    }

    @SuppressWarnings("unchecked")
    private void refreshTableandTotals() {
        ((OrderApp<?>) stage).iterateDetailTableItems((List<Priced>) table.getItems());
    }
}
