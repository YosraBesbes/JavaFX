package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.util.FX;
import ph.txtdis.type.QualityType;

public abstract class AbstractQualityDetailTable<E, D> extends AbstractOrderDetailTable<E, D> {

    public AbstractQualityDetailTable(Stage stage, D dto) {
        super(stage, dto);
    }

    @Override
    protected void addTableColumns() {
        super.addTableColumns();
        TableColumn<E, QualityType> qualityCol = FX.addComboColumn("Quality", "quality", QualityType.values(), 50);
        table.getColumns().add(qualityCol);
    }
}
