package ph.txtdis.fx.tablecolumn;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

public abstract class AbstractDisplayColumn<S, E> extends AbstractTableColumn<S, E> {
    private Pos pos;

    public AbstractDisplayColumn(Stage stage, String text, String field, int minWidth, Pos pos) {
        super(stage, text, field, minWidth);
        this.pos = pos;
        setEditable(false);
    }

    @Override
    protected Callback<TableColumn<S, E>, TableCell<S, E>> getCallback(Stage stage) {
        return column -> getTableCell(stage, pos);
    }

    protected abstract TableCell<S, E> getTableCell(Stage stage, Pos pos);
}