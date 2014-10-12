package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.util.FX;

public abstract class AbstractDaysLevelDetailTable<E, D> extends AbstractPriceTable<E, D> {

    public AbstractDaysLevelDetailTable(Stage stage, D dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        super.addTableColumns();
        TableColumn<E, Integer> beforeCol = FX.addIntegerColumn("Before", "daysLevelBefore");
        TableColumn<E, Integer> afterCol = FX.addIntegerColumn("After", "daysLevelAfter");
        TableColumn<E, Integer> daysLevelCol = new TableColumn<>("Days Level");
        daysLevelCol.getColumns().addAll(beforeCol, afterCol);
        
        table.getColumns().add(daysLevelCol);

        beforeCol.setEditable(false);
        afterCol.setEditable(false);
    }
}
