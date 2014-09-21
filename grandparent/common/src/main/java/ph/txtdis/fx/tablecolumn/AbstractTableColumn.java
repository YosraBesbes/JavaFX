package ph.txtdis.fx.tablecolumn;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public abstract class AbstractTableColumn<S, E> extends TableColumn<S, E> {

    public AbstractTableColumn(Stage stage, String text, String field, int minWidth) {
        super(text);
        setCellValueFactory(new PropertyValueFactory<>(field));
        setMinWidth(minWidth);
        setCellFactory(getCallback(stage));
        setId(field);
    }

    protected abstract Callback<TableColumn<S, E>, TableCell<S, E>> getCallback(Stage stage);
}