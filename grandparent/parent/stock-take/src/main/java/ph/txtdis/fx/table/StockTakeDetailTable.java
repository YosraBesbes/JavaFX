package ph.txtdis.fx.table;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.dto.StockTakeDTO;
import ph.txtdis.fx.dialog.StockTakeDialog;
import ph.txtdis.model.StockTakeDetail;

public class StockTakeDetailTable extends AbstractQualityDetailTable<StockTakeDetail, StockTakeDTO> {

    public StockTakeDetailTable(Stage stage, StockTakeDTO dto) {
        super(stage, dto);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new StockTakeDialog(stage, dto);
    }

    @Override
    protected QualityRated getQualityDTO() {
        return App.getContext().getBean(QualityRated.class);
    }
}
