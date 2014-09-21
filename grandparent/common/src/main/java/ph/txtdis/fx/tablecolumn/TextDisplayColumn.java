package ph.txtdis.fx.tablecolumn;

import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.DoubleClickTableCell;

public class TextDisplayColumn<S> extends AbstractDisplayColumn<S, String> {

    public TextDisplayColumn(Stage stage, String text, String field, int minWidth) {
        super(stage, text, field, minWidth);
    }

    @Override
    protected TableCell<S, String> getTableCell(Stage stage) {
        return new DoubleClickTableCell<S, String>(stage);
    }
}
