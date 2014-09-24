package ph.txtdis.fx.table;

import javafx.scene.control.ContextMenu;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.InvoicingDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.model.InvoicingDetail;

public class InvoicingDetailTable extends AbstractQualityDetailTable<InvoicingDetail, InvoicingDTO> {

    public InvoicingDetailTable(Stage stage, InvoicingDTO dto) {
        super(stage, dto);
    }

    @Override
    protected void createInputDialog() {
    }

    @Override
    protected void createTableContextMenu(ContextMenu contextMenu) {
    }

    @Override
    protected QualityRated getQualityDTO() {
        return App.getContext().getBean(QualityRated.class);
    }
}
