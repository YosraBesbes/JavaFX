package ph.txtdis.fx.tablecolumn;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.IdDisplayTableCell;

public class IdDisplayColumn<S> extends AbstractDisplayColumn<S, Integer> {

    public IdDisplayColumn(Stage stage, String text, String field) {
        super(stage, text, field, 80, null);
    }

    @Override
    protected TableCell<S, Integer> getTableCell(Stage stage, Pos pos) {
        return new IdDisplayTableCell<S>();
    }
}
