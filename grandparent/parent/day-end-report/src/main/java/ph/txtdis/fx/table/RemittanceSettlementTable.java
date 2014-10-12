package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.DateDisplayColumn;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.MonetaryDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextInputColumn;
import ph.txtdis.model.RemittanceSettlementFilteredDetail;

public class RemittanceSettlementTable extends AbstractTable<RemittanceSettlementFilteredDetail> {

    @SuppressWarnings("unchecked")
    public RemittanceSettlementTable(Stage stage) {
        table.setEditable(true);
        table.setTooltip(new Tooltip("Double-click \"Adjustment\" or \"Action Taken\"\ncolumn cell to enter data"));

        TableColumn<RemittanceSettlementFilteredDetail, Integer> invoiceIdCol = new IdDisplayColumn<>(stage, "S/I No.",
                "invoiceId");
        TableColumn<RemittanceSettlementFilteredDetail, LocalDate> dateCol = new DateDisplayColumn<>(stage, "Date",
                "date");
        TableColumn<RemittanceSettlementFilteredDetail, String> customerCol = new TextDisplayColumn<>(stage,
                "Customer", "partner", 180, Pos.CENTER_LEFT);
        TableColumn<RemittanceSettlementFilteredDetail, BigDecimal> invoicedCol = new MonetaryDisplayColumn<>(stage,
                "Invoiced", "invoicedValue");
        TableColumn<RemittanceSettlementFilteredDetail, BigDecimal> remittedCol = new MonetaryDisplayColumn<>(stage,
                "Remitted", "remittedValue");
        TableColumn<RemittanceSettlementFilteredDetail, BigDecimal> adjustmentCol = new MonetaryDisplayColumn<>(stage,
                "Adjustment", "adjustmentValue");
        TableColumn<RemittanceSettlementFilteredDetail, BigDecimal> varianceCol = new MonetaryDisplayColumn<>(stage,
                "Over / (Short)", "varianceValue");
        TableColumn<RemittanceSettlementFilteredDetail, String> actionTakenCol = new TextInputColumn<>(stage,
                "Action Taken", "actionTaken", 320);
        table.getColumns().addAll(invoiceIdCol, dateCol, customerCol, invoicedCol, remittedCol, adjustmentCol,
                varianceCol, actionTakenCol);
    }
}
