package ph.txtdis.fx.table;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;

public abstract class AbstractQualityDetailTable<E, D> extends AbstractOrderDetailTable<E, D> {

    public AbstractQualityDetailTable(Stage stage, D dto) {
        super(stage, dto);
    }

    @Override
    protected void addTableColumns() {
        super.addTableColumns();
        TableColumn<E, String> qualityCol = new TextDisplayColumn<>(stage, "Quality", "quality", 70, Pos.CENTER);
        table.getColumns().add(qualityCol);
    }

    protected abstract QualityRated getQualityDTO();
}
