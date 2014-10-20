package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecolumn.CurrencyDisplayColumn;
import ph.txtdis.fx.tablecolumn.DateDisplayColumn;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.Vat;

public class VatTable extends AbstractTable<Vat> {

    @SuppressWarnings("unchecked")
    public VatTable(Stage stage) {
        TableColumn<Vat, Integer> idCol = new IdDisplayColumn<>(stage, "S/I No.", "id");
        TableColumn<Vat, LocalDate> dateCol = new DateDisplayColumn<>(stage, "Date", "date");
        TableColumn<Vat, String> partnerCol = new TextDisplayColumn<>(stage, "Customer", "partner", 180,
                Pos.CENTER_LEFT);
        TableColumn<Vat, BigDecimal> valueCol = new CurrencyDisplayColumn<>(stage, "Value", "totalValue");
        TableColumn<Vat, BigDecimal> vatCol = new CurrencyDisplayColumn<>(stage, "VAT", "vatValue");
        table.getColumns().addAll(idCol, dateCol, partnerCol, valueCol, vatCol);
    }
}
