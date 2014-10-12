package ph.txtdis.fx.tablecolumn;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.DisplayTableCell;

public class TextDisplayColumn<S> extends AbstractDisplayColumn<S, String> {

    public TextDisplayColumn(Stage stage, String text, String field, int minWidth, Pos pos) {
        super(stage, text, field, minWidth, pos);
    }

    @Override
    protected TableCell<S, String> getTableCell(Stage stage, Pos pos) {
        return new DisplayTableCell<S, String>(pos);
    }
}
