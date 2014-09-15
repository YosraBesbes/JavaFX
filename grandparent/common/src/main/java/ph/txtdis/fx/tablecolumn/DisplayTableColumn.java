package ph.txtdis.fx.tablecolumn;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.DoubleClickTableCell;

public class DisplayTableColumn<S, E> extends TableColumn<S, E> {

    public DisplayTableColumn(Stage stage, String text, String field, int minWidth) {
        super(text);
        setCellValueFactory(new PropertyValueFactory<>(field));
        setMinWidth(minWidth);
        setCellFactory(column -> {
            return getTableCell(stage);
        });
    }

    protected TableCell<S, E> getTableCell(Stage stage) {
        return new DoubleClickTableCell<S, E>(stage);
    };
}