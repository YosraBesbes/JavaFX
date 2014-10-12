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
import ph.txtdis.model.LoadSettlementFilteredDetail;

public class LoadSettlementDetailTable extends AbstractTable<LoadSettlementFilteredDetail> {

    @SuppressWarnings("unchecked")
    public LoadSettlementDetailTable(Stage stage) {
        table.setEditable(true);
        table.setTooltip(new Tooltip("Double-click \"Adjustment\" or \"Action Taken\"\ncolumn cell to enter data"));

        TableColumn<LoadSettlementFilteredDetail, Integer> itemIdCol = new IdDisplayColumn<>(stage, "Item No.",
                "itemId");
        TableColumn<LoadSettlementFilteredDetail, String> itemNameCol = new TextDisplayColumn<>(stage, "Name", "item",
                180, Pos.CENTER_LEFT);
        TableColumn<LoadSettlementFilteredDetail, BigDecimal> pickedCol = new QtyDisplayColumn<>(stage, "Picked",
                "pickedQty", 80);
        TableColumn<LoadSettlementFilteredDetail, BigDecimal> soldCol = new QtyDisplayColumn<>(stage, "Sold",
                "soldQty", 80);
        TableColumn<LoadSettlementFilteredDetail, BigDecimal> returnedCol = new QtyDisplayColumn<>(stage, "Returns",
                "returnedQty", 80);
        TableColumn<LoadSettlementFilteredDetail, BigDecimal> adjustmentCol = new QtyInputColumn<>(stage,
                "Adjust-ment", "adjustmentQty", 80);
        TableColumn<LoadSettlementFilteredDetail, BigDecimal> varianceCol = new QtyDisplayColumn<>(stage,
                "Over / (Short)", "varianceQty", 80);
        TableColumn<LoadSettlementFilteredDetail, String> actionTakenCol = new TextInputColumn<>(stage, "Action Taken",
                "actionTaken", 320);
        table.getColumns().addAll(itemIdCol, itemNameCol, pickedCol, soldCol, returnedCol, adjustmentCol, varianceCol,
                actionTakenCol);
    }
}
