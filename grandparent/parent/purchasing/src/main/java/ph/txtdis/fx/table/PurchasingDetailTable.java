package ph.txtdis.fx.table;

import javafx.stage.Stage;
import ph.txtdis.dto.PurchasingDTO;
import ph.txtdis.fx.dialog.PurchasingDialog;
import ph.txtdis.model.PurchasingDetail;

public class PurchasingDetailTable extends AbstractDaysLevelDetailTable<PurchasingDetail, PurchasingDTO> {

    public PurchasingDetailTable(Stage stage, PurchasingDTO dto) {
        super(stage, dto);
        table.getColumns().forEach((column) -> column.setEditable(false));
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new PurchasingDialog(stage, dto);
    }
}
