package ph.txtdis.fx.table;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.fx.dialog.BookingDialog;
import ph.txtdis.model.BookingDetail;

public class BookingDetailTable extends AbstractPriceDetailTable<BookingDetail, BookingDTO> {

    public BookingDetailTable(Stage stage, BookingDTO dto) {
        super(stage, dto);
        table.getColumns().forEach((column) -> column.setEditable(false));
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new BookingDialog(stage, dto);
    }

    @Override
    protected QualityRated getQualityDTO() {
        return App.getContext().getBean(QualityRated.class);
    }
}
