package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Quality;

public abstract class AbstractQualityDetailTable<E, D> extends AbstractOrderDetailTable<E, D> {

    public AbstractQualityDetailTable(Stage stage, D dto) {
        super(stage, dto);
    }

    @Override
    protected void addTableColumns() {
        super.addTableColumns();
        TableColumn<E, Quality> qualityCol = FX.addComboColumn("Quality", "quality", getQualityDTO().list(), 50);
        table.getColumns().add(qualityCol);
    }

    protected abstract QualityRated getQualityDTO();
}
