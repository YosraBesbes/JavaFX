package ph.txtdis.fx.tablecolumn;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.DoubleClickIdTableCell;

public class IdDisplayColumn<S> extends AbstractDisplayColumn<S, Integer> {

    public IdDisplayColumn(Stage stage, String text, String field, int minWidth) {
        super(stage, text, field, minWidth, Pos.CENTER_LEFT);
    }

    @Override
    protected TableCell<S, Integer> getTableCell(Stage stage, Pos pos) {
        return new DoubleClickIdTableCell<S>(stage);
    }
}
