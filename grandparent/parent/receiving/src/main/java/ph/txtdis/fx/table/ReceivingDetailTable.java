package ph.txtdis.fx.table;

import javafx.scene.control.ContextMenu;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.dto.ReceivingDTO;
import ph.txtdis.model.ReceivingDetail;

public class ReceivingDetailTable extends AbstractPriceDetailTable<ReceivingDetail, ReceivingDTO> {

    public ReceivingDetailTable(Stage stage, ReceivingDTO dto) {
        super(stage, dto);
    }

    @Override
    protected void createTableContextMenu(ContextMenu contextMenu) {
    }

    @Override
    protected void createInputDialog() {
    }

    @Override
    protected QualityRated getQualityDTO() {
        return App.getContext().getBean(QualityRated.class);
    }
}
