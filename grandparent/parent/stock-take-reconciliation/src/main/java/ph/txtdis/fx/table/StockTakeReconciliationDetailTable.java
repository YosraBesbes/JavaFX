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
import ph.txtdis.model.StockTakeReconciliationFilteredDetail;

public class StockTakeReconciliationDetailTable extends AbstractTable<StockTakeReconciliationFilteredDetail> {

    @SuppressWarnings("unchecked")
    public StockTakeReconciliationDetailTable(Stage stage) {
        table.setEditable(true);
        table.setTooltip(new Tooltip("Double-click \"Adjust\" or \"Justification\"\ncolumn cell to enter data"));
        table.setId("Stock Take Reconciliation");

        TableColumn<StockTakeReconciliationFilteredDetail, Integer> itemIdCol = new IdDisplayColumn<>(stage,
                "Item No.", "itemId");
        TableColumn<StockTakeReconciliationFilteredDetail, String> itemNameCol = new TextDisplayColumn<>(stage, "Name",
                "item", 180, Pos.CENTER_LEFT);
        TableColumn<StockTakeReconciliationFilteredDetail, String> qualityCol = new TextDisplayColumn<>(stage,
                "Quality", "qualityType", 70, Pos.CENTER);
        TableColumn<StockTakeReconciliationFilteredDetail, BigDecimal> startCol = new QtyDisplayColumn<>(stage,
                "System", "systemQty", 80);
        TableColumn<StockTakeReconciliationFilteredDetail, BigDecimal> countCol = new QtyDisplayColumn<>(stage,
                "Count", "countQty", 80);
        TableColumn<StockTakeReconciliationFilteredDetail, BigDecimal> adjustmentCol = new QtyInputColumn<>(stage,
                "Adjust-ment", "adjustmentQty", 80);
        TableColumn<StockTakeReconciliationFilteredDetail, BigDecimal> finalCol = new QtyInputColumn<>(stage, "Final",
                "finalQty", 80);
        TableColumn<StockTakeReconciliationFilteredDetail, String> justificationCol = new TextInputColumn<>(stage,
                "Justification", "justification", 240);
        TableColumn<StockTakeReconciliationFilteredDetail, BigDecimal> varianceCol = new QtyDisplayColumn<>(stage,
                "Gain / (Loss)", "varianceQty", 80);
        table.getColumns().addAll(itemIdCol, itemNameCol, qualityCol, startCol, countCol, adjustmentCol, finalCol,
                varianceCol, justificationCol);
    }
}
