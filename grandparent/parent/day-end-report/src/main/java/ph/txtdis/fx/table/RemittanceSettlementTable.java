package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.CurrencyDisplayColumn;
import ph.txtdis.fx.tablecolumn.DateDisplayColumn;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextInputColumn;
import ph.txtdis.model.RemittanceSettlementDetail;

public class RemittanceSettlementTable extends AbstractTable<RemittanceSettlementDetail> {

    @SuppressWarnings("unchecked")
    public RemittanceSettlementTable(Stage stage) {
        table.setEditable(true);
        table.setTooltip(new Tooltip("Double-click \"Action Taken\"\ncolumn cell to enter data"));
        table.setPlaceholder(new Label("Everything's in order"));

        TableColumn<RemittanceSettlementDetail, Integer> invoiceIdCol = new IdDisplayColumn<>(stage, "S/I No.",
                "invoiceId");
        TableColumn<RemittanceSettlementDetail, LocalDate> dateCol = new DateDisplayColumn<>(stage, "Date", "date");
        TableColumn<RemittanceSettlementDetail, String> customerCol = new TextDisplayColumn<>(stage, "Customer",
                "partner", 180, Pos.CENTER_LEFT);
        TableColumn<RemittanceSettlementDetail, BigDecimal> invoicedCol = new CurrencyDisplayColumn<>(stage,
                "Invoiced", "invoicedValue");
        TableColumn<RemittanceSettlementDetail, BigDecimal> remittedCol = new CurrencyDisplayColumn<>(stage,
                "Remitted", "remittedValue");
        TableColumn<RemittanceSettlementDetail, BigDecimal> varianceCol = new CurrencyDisplayColumn<>(stage,
                "Over / (Short)", "varianceValue");
        TableColumn<RemittanceSettlementDetail, String> actionTakenCol = new TextInputColumn<>(stage, "Action Taken",
                "actionTaken", 320);
        table.getColumns().addAll(invoiceIdCol, dateCol, customerCol, invoicedCol, remittedCol, varianceCol,
                actionTakenCol);
    }
}
