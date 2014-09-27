package ph.txtdis.fx.tablecolumn;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.DoubleClickTableCell;

public class TextDisplayColumn<S> extends AbstractDisplayColumn<S, String> {

    public TextDisplayColumn(Stage stage, String text, String field, int minWidth, Pos pos) {
        super(stage, text, field, minWidth, pos);
    }

    @Override
    protected TableCell<S, String> getTableCell(Stage stage, Pos pos) {
        return new DoubleClickTableCell<S, String>(stage, pos);
    }
}
