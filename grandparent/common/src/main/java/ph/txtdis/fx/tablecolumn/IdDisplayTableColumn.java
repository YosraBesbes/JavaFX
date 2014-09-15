package ph.txtdis.fx.tablecolumn;

import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.DoubleClickIdTableCell;

public class IdDisplayTableColumn<S> extends DisplayTableColumn<S, Integer> {

    public IdDisplayTableColumn(Stage stage, String text, String field, int minWidth) {
        super(stage, text, field, minWidth);
    }

    @Override
    protected TableCell<S, Integer> getTableCell(Stage stage) {
        return new DoubleClickIdTableCell<S>(stage);
    }
}
