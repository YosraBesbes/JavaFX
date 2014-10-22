package ph.txtdis.fx.table;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.PurchasingDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.fx.dialog.PurchasingDialog;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.PurchasingDetail;

public class PurchasingTable extends AbstractPriceTable<PurchasingDetail, PurchasingDTO> {

    @Override
    protected void addTableColumns() {
        super.addTableColumns();
        TableColumn<PurchasingDetail, String> daysLevelCol = new TextDisplayColumn<>(stage, "Days Level", "daysLevel",
                80, Pos.CENTER_LEFT);
        table.getColumns().add(daysLevelCol);
    }

    public PurchasingTable(Stage stage, PurchasingDTO dto) {
        super(stage, dto);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new PurchasingDialog(stage, dto);
    }

    @Override
    protected QualityRated getQualityDTO() {
        return App.getContext().getBean(QualityRated.class);
    }
}
