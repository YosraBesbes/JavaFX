package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.Item;
import ph.txtdis.model.Quality;
import ph.txtdis.printer.SalesOrderPrinter;
import ph.txtdis.type.UomType;

public class BookingDialog extends AbstractOrderDialog<Booking, BookingDetail, BookingDTO> {

    public BookingDialog(Stage stage, BookingDTO dto, int lineNo) {
        super("Booking", stage, dto, lineNo);
    }

    @Override
    protected QualityRated getQuality() {
        return App.getContext().getBean(QualityRated.class);
    }

    @Override
    protected ItemDTO getItemDTO() {
        return App.getContext().getBean(ItemDTO.class);
    }

    @Override
    protected BookingDetail getEntity(Booking booking, Item item, UomType uom, BigDecimal qty, Quality quality) {
        return new BookingDetail(booking, item, uom, qty, quality);
    }

    @Override
    protected void addItems(BookingDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        if (++lineNo > SalesOrderPrinter.LINES_PER_PAGE)
            close();
    }
}
