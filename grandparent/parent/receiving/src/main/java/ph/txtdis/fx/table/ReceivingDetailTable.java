package ph.txtdis.fx.table;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.dto.ReceivingDTO;
import ph.txtdis.fx.dialog.ReceivingDialog;
import ph.txtdis.model.ReceivingDetail;

public class ReceivingDetailTable extends AbstractPriceTable<ReceivingDetail, ReceivingDTO> {

    public ReceivingDetailTable(Stage stage, ReceivingDTO dto) {
        super(stage, dto);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new ReceivingDialog(stage, dto);
    }

    @Override
    protected QualityRated getQualityDTO() {
        return App.getContext().getBean(QualityRated.class);
    }
}
