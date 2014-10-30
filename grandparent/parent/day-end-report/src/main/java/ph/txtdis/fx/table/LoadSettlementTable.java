package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.QtyDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextInputColumn;
import ph.txtdis.model.LoadSettlementDetail;

public class LoadSettlementTable extends AbstractTable<LoadSettlementDetail> {

    @SuppressWarnings("unchecked")
    public LoadSettlementTable(Stage stage) {
        table.setEditable(true);
        table.setTooltip(new Tooltip("Double-click \"Action Taken\"\ncolumn cell to enter data"));
        table.setPlaceholder(new Label("Everything's in order"));

        TableColumn<LoadSettlementDetail, Integer> itemIdCol = new IdDisplayColumn<>(stage, "Item No.", "itemId");
        TableColumn<LoadSettlementDetail, String> itemNameCol = new TextDisplayColumn<>(stage, "Name", "item", 180,
                Pos.CENTER_LEFT);
        TableColumn<LoadSettlementDetail, BigDecimal> pickedCol = new QtyDisplayColumn<>(stage, "Picked", "pickedQty",
                80);
        TableColumn<LoadSettlementDetail, BigDecimal> soldCol = new QtyDisplayColumn<>(stage, "Sold", "soldQty", 80);
        TableColumn<LoadSettlementDetail, BigDecimal> returnedCol = new QtyDisplayColumn<>(stage, "Returns",
                "returnedQty", 80);
        TableColumn<LoadSettlementDetail, BigDecimal> varianceCol = new QtyDisplayColumn<>(stage, "Over / (Short)",
                "varianceQty", 80);
        TableColumn<LoadSettlementDetail, String> actionTakenCol = new TextInputColumn<>(stage, "Action Taken",
                "actionTaken", 320);
        table.getColumns().addAll(itemIdCol, itemNameCol, pickedCol, soldCol, returnedCol, varianceCol, actionTakenCol);
    }
}
