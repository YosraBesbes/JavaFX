package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.CurrencyDisplayColumn;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.Aging;

public class AgingTable extends AbstractTable<Aging> {

    @SuppressWarnings("unchecked")
    public AgingTable(Stage stage) {
        TableColumn<Aging, Integer> idCol = new IdDisplayColumn<>(stage, "ID No.", "customerId");
        TableColumn<Aging, String> partnerCol = new TextDisplayColumn<>(stage, "Customer", "customer", 180,
                Pos.CENTER_LEFT);
        TableColumn<Aging, BigDecimal> totalCol = new CurrencyDisplayColumn<>(stage, "Total", "totalValue");
        TableColumn<Aging, BigDecimal> currentCol = new CurrencyDisplayColumn<>(stage, "Current", "currentValue");
        TableColumn<Aging, BigDecimal> oneToSevenCol = new CurrencyDisplayColumn<>(stage, "1 - 7", "oneToSevenValue");
        TableColumn<Aging, BigDecimal> eightToFifteenCol = new CurrencyDisplayColumn<>(stage, "8 - 15",
                "eightToFifteenValue");
        TableColumn<Aging, BigDecimal> sixteenToThirtyCol = new CurrencyDisplayColumn<>(stage, "16 - 30",
                "sixteenToThirtyValue");
        TableColumn<Aging, BigDecimal> greaterThanThirtyCol = new CurrencyDisplayColumn<>(stage, ">30",
                "greaterThanThirtyValue");
        table.getColumns().addAll(idCol, partnerCol, totalCol, currentCol, oneToSevenCol, eightToFifteenCol,
                sixteenToThirtyCol, greaterThanThirtyCol);
    }
}
