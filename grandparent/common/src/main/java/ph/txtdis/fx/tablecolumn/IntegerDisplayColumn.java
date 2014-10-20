package ph.txtdis.fx.tablecolumn;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.IntegerDisplayTableCell;

public class IntegerDisplayColumn<S> extends AbstractDisplayColumn<S, Integer> {

    public IntegerDisplayColumn(Stage stage, String columnName, String fieldWithCountSuffix) {
        super(stage, columnName, fieldWithCountSuffix, 80, null);
    }

    @Override
    protected TableCell<S, Integer> getTableCell(Stage stage, Pos pos) {
        return new IntegerDisplayTableCell<S>();
    }
}
