package ph.txtdis.fx.table;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ContextMenu;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.InvoicingDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.fx.dialog.Invoiced;
import ph.txtdis.fx.dialog.InvoicingDialog;
import ph.txtdis.model.InvoicingDetail;
import ph.txtdis.printer.SalesOrderPrinter;

public class InvoicingDetailTable extends AbstractPriceTable<InvoicingDetail, InvoicingDTO> {
    private static SimpleIntegerProperty lineNo = new SimpleIntegerProperty();

    public InvoicingDetailTable(Stage stage, InvoicingDTO dto) {
        super(stage, dto);
        lineNo.set(table.getItems().size());
    }

    @Override
    protected QualityRated getQualityDTO() {
        return App.getContext().getBean(QualityRated.class);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new InvoicingDialog(stage, dto, lineNo.get());
    }

    @Override
    protected void addTableItems() {
        super.addTableItems();
        lineNo.set(((Invoiced) inputDialog).getLineNo());
    }

    @Override
    public void setTableContextMenu() {
        table.contextMenuProperty().bind(
                Bindings.when(Bindings.greaterThan(SalesOrderPrinter.LINES_PER_PAGE, lineNo)).then(createContextMenu())
                        .otherwise((ContextMenu) null));
    }
}
