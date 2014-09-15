package ph.txtdis.fx.table;

import java.time.LocalDate;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.AuditedDTO;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Booking;

public class BookingDateTable {
    private final TableView<Booking> table;

    @SuppressWarnings("unchecked")
    public BookingDateTable(Stage stage, BookingDTO purchasingDTO) {
        AuditedDTO<Booking> dto = (AuditedDTO<Booking>) purchasingDTO;
        table = new TableView<>();
        TableColumn<Booking, Integer> idCol = FX.addDisplayColumn(stage, "ID", "id", 50, dto);
        TableColumn<Booking, LocalDate> nameCol = FX.addDisplayColumn(stage, "Date", "orderDate", 120, dto);
        TableColumn<Booking, String> descriptionCol = FX.addDisplayColumn(stage, "Booked For", "partnerName", 240,
                dto);
        table.getColumns().addAll(idCol, nameCol, descriptionCol);
    }

    public TableView<Booking> getTable() {
        return table;
    }
}
