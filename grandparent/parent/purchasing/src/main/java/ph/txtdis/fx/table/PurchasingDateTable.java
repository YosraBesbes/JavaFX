package ph.txtdis.fx.table;

import java.time.LocalDate;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.AuditedDTO;
import ph.txtdis.dto.PurchasingDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Purchasing;

public class PurchasingDateTable {
    private final TableView<Purchasing> table;

    @SuppressWarnings("unchecked")
    public PurchasingDateTable(Stage stage, PurchasingDTO purchasingDTO) {
        AuditedDTO<Purchasing> dto = (AuditedDTO<Purchasing>) purchasingDTO;
        table = new TableView<>();
        TableColumn<Purchasing, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Purchasing, LocalDate> nameCol = FX.addDisplayColumn(stage, "Date", "orderDate", 120, dto);
        TableColumn<Purchasing, String> descriptionCol = FX.addDisplayColumn(stage, "Ordered From", "partnerName", 240,
                dto);
        table.getColumns().addAll(idCol, nameCol, descriptionCol);
    }

    public TableView<Purchasing> getTable() {
        return table;
    }
}
