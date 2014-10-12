package ph.txtdis.fx.table;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ContextMenu;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.fx.dialog.BookingDialog;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.printer.SalesOrderPrinter;

public class BookingTable extends AbstractPriceTable<BookingDetail, BookingDTO> {
    private static SimpleIntegerProperty lineNo = new SimpleIntegerProperty(0);

    public BookingTable(Stage stage, BookingDTO dto) {
        super(stage, dto);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new BookingDialog(stage, dto, lineNo.get());
    }

    @Override
    protected QualityRated getQualityDTO() {
        return App.getContext().getBean(QualityRated.class);
    }

    @Override
    protected void addTableItems() {
        super.addTableItems();
        lineNo.set(((BookingDialog) inputDialog).getLineNo());
    }

    @Override
    public void setTableContextMenu() {
        table.contextMenuProperty().bind(
                Bindings.when(Bindings.greaterThan(SalesOrderPrinter.LINES_PER_PAGE, lineNo)).then(createContextMenu())
                        .otherwise((ContextMenu) null));
    }

    public void resetLineNo() {
        lineNo.set(0);
    }
}
