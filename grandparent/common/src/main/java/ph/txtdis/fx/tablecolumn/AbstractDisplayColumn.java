package ph.txtdis.fx.tablecolumn;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

public abstract class AbstractDisplayColumn<S, E> extends AbstractTableColumn<S, E> {

    public AbstractDisplayColumn(Stage stage, String text, String field, int minWidth) {
        super(stage, text, field, minWidth);
        setEditable(false);
    }

    @Override
    protected Callback<TableColumn<S, E>, TableCell<S, E>> getCallback(Stage stage) {
        return column -> getTableCell(stage);
    }

    protected abstract TableCell<S, E> getTableCell(Stage stage);
}