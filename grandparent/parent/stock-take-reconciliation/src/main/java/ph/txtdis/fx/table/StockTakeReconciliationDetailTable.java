package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.QtyDisplayColumn;
import ph.txtdis.fx.tablecolumn.QtyInputColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextInputColumn;
import ph.txtdis.model.FxStockTakeReconciliationDetail;

public class StockTakeReconciliationDetailTable extends AbstractTable<FxStockTakeReconciliationDetail> {

    @SuppressWarnings("unchecked")
    public StockTakeReconciliationDetailTable(Stage stage) {
        table.setEditable(true);
        table.setTooltip(new Tooltip("Double-click \"Adjust\" or \"Justification\"\ncolumn cell to enter data"));
        table.setId("Stock Take Reconciliation");

        TableColumn<FxStockTakeReconciliationDetail, Integer> itemIdCol = new IdDisplayColumn<>(stage,
                "Item No.", "itemId");
        TableColumn<FxStockTakeReconciliationDetail, String> itemNameCol = new TextDisplayColumn<>(stage, "Name",
                "item", 180, Pos.CENTER_LEFT);
        TableColumn<FxStockTakeReconciliationDetail, String> qualityCol = new TextDisplayColumn<>(stage,
                "Quality", "qualityType", 70, Pos.CENTER);
        TableColumn<FxStockTakeReconciliationDetail, BigDecimal> startCol = new QtyDisplayColumn<>(stage,
                "System", "systemQty", 80);
        TableColumn<FxStockTakeReconciliationDetail, BigDecimal> countCol = new QtyDisplayColumn<>(stage,
                "Count", "countQty", 80);
        TableColumn<FxStockTakeReconciliationDetail, BigDecimal> adjustmentCol = new QtyInputColumn<>(stage,
                "Adjust-ment", "adjustmentQty", 80);
        TableColumn<FxStockTakeReconciliationDetail, BigDecimal> finalCol = new QtyInputColumn<>(stage, "Final",
                "finalQty", 80);
        TableColumn<FxStockTakeReconciliationDetail, String> justificationCol = new TextInputColumn<>(stage,
                "Justification", "justification", 240);
        TableColumn<FxStockTakeReconciliationDetail, BigDecimal> varianceCol = new QtyDisplayColumn<>(stage,
                "Gain / (Loss)", "varianceQty", 80);
        table.getColumns().addAll(itemIdCol, itemNameCol, qualityCol, startCol, countCol, adjustmentCol, finalCol,
                varianceCol, justificationCol);
    }
}
