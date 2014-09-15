package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.DisplayTableColumn;
import ph.txtdis.fx.tablecolumn.IdDisplayTableColumn;
import ph.txtdis.fx.tablecolumn.QtyDisplayTableColumn;
import ph.txtdis.model.StockTakeReconciliationDetail;

public class StockTakeReconciliationDetailTable extends AbstractTable<StockTakeReconciliationDetail> {

    @SuppressWarnings("unchecked")
    public StockTakeReconciliationDetailTable(Stage stage) {
        TableColumn<StockTakeReconciliationDetail, Integer> itemIdCol = new IdDisplayTableColumn<StockTakeReconciliationDetail>(
                stage, "Item ID", "itemId", 80);
        TableColumn<StockTakeReconciliationDetail, String> itemNameCol = new DisplayTableColumn<StockTakeReconciliationDetail, String>(
                stage, "Name", "itemName", 180);
        TableColumn<StockTakeReconciliationDetail, String> uomCol = new DisplayTableColumn<StockTakeReconciliationDetail, String>(
                stage, "Quality", "quality", 40);
        TableColumn<StockTakeReconciliationDetail, BigDecimal> startCol = new QtyDisplayTableColumn<StockTakeReconciliationDetail>(
                stage, "System", "systemQty", 80);
        TableColumn<StockTakeReconciliationDetail, BigDecimal> countCol = new QtyDisplayTableColumn<StockTakeReconciliationDetail>(
                stage, "Actual", "actualQty", 80);
        TableColumn<StockTakeReconciliationDetail, BigDecimal> varianceCol = new QtyDisplayTableColumn<StockTakeReconciliationDetail>(
                stage, "Variance", "varianceQty", 80);
        TableColumn<StockTakeReconciliationDetail, BigDecimal> adjustmentCol = new QtyDisplayTableColumn<StockTakeReconciliationDetail>(
                stage, "Adjust", "adjustmentQty", 80);
        TableColumn<StockTakeReconciliationDetail, BigDecimal> finalCol = new QtyDisplayTableColumn<StockTakeReconciliationDetail>(
                stage, "Final", "finalQty", 80);
        TableColumn<StockTakeReconciliationDetail, String> justificationCol = new DisplayTableColumn<StockTakeReconciliationDetail, String>(
                stage, "Justification", "justification", 240);
        table.getColumns().addAll(itemIdCol, itemNameCol, uomCol, startCol, countCol, varianceCol, adjustmentCol,
                finalCol, justificationCol);
    }
}
