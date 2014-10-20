package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.CurrencyDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.Invoicing;

public class RevenueTable extends AbstractTable<Invoicing> {

    @SuppressWarnings("unchecked")
    public RevenueTable(Stage stage) {
        TableColumn<Invoicing, Integer> idCol = new IdDisplayColumn<>(stage, "S/I No.", "id");
        TableColumn<Invoicing, String> partnerCol = new TextDisplayColumn<>(stage, "Customer", "partner", 180,
                Pos.CENTER_LEFT);
        TableColumn<Invoicing, BigDecimal> valueCol = new CurrencyDisplayColumn<>(stage, "Value", "totalValue");
        table.getColumns().addAll(idCol, partnerCol, valueCol);
        table.setId("Revenue");
    }
}
