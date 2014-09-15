package ph.txtdis.fx.table;

import java.time.LocalDate;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.AuditedDTO;
import ph.txtdis.dto.ReceivingDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Receiving;

public class ReceivingDateTable {
    private final TableView<Receiving> table;

    @SuppressWarnings("unchecked")
    public ReceivingDateTable(Stage stage, ReceivingDTO receivingDTO) {
        AuditedDTO<Receiving> dto = (AuditedDTO<Receiving>) receivingDTO;
        table = new TableView<>();
        TableColumn<Receiving, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Receiving, LocalDate> nameCol = FX.addDisplayColumn(stage, "Date", "orderDate", 120, dto);
        TableColumn<Receiving, String> descriptionCol = FX.addDisplayColumn(stage, "Received From", "partnerName", 240,
                dto);
        table.getColumns().addAll(idCol, nameCol, descriptionCol);
    }

    public TableView<Receiving> getTable() {
        return table;
    }
}
