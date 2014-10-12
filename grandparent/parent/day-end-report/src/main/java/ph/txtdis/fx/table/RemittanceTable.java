package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.DateDisplayColumn;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.MonetaryDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.Remittance;

public class RemittanceTable extends AbstractTable<Remittance> {

    @SuppressWarnings("unchecked")
    public RemittanceTable(Stage stage) {
        TableColumn<Remittance, Integer> idCol = new IdDisplayColumn<>(stage, "R/S No.", "id");
        TableColumn<Remittance, String> typeCol = new TextDisplayColumn<>(stage, "Type", "type", 80, Pos.CENTER_LEFT);
        TableColumn<Remittance, String> partnerCol = new TextDisplayColumn<>(stage, "Partner", "partner", 180,
                Pos.CENTER_LEFT);
        TableColumn<Remittance, LocalDate> dateCol = new DateDisplayColumn<>(stage, "Date", "orderDate");
        TableColumn<Remittance, BigDecimal> valueCol = new MonetaryDisplayColumn<>(stage, "Value", "totalValue");
        table.getColumns().addAll(idCol, typeCol, partnerCol, dateCol, valueCol);
        table.setId("Remittance");
    }
}
