package ph.txtdis.fx.table;

import java.time.LocalDate;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.Audited;
import ph.txtdis.dto.InvoicingDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Invoicing;

public class InvoicingDateTable {
    private final TableView<Invoicing> table;

    @SuppressWarnings("unchecked")
    public InvoicingDateTable(Stage stage, InvoicingDTO receivingDTO) {
        Audited<Invoicing> dto = (Audited<Invoicing>) receivingDTO;
        table = new TableView<>();
        TableColumn<Invoicing, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Invoicing, LocalDate> nameCol = FX.addDisplayColumn(stage, "Date", "orderDate", 120, dto);
        TableColumn<Invoicing, String> descriptionCol = FX.addDisplayColumn(stage, "Sold To", "partnerName", 240,
                dto);
        table.getColumns().addAll(idCol, nameCol, descriptionCol);
    }

    public TableView<Invoicing> getTable() {
        return table;
    }
}
