package ph.txtdis.fx.tablecolumn;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TextInputColumn<S> extends AbstractInputColumn<S, String> {

    public TextInputColumn(Stage stage, String text, String field, int minWidth) {
        super(stage, text, field, minWidth);
    }

    @Override
    protected Callback<TableColumn<S, String>, TableCell<S, String>> getCallback(Stage stage, Pos pos) {
        return TextFieldTableCell.<S> forTableColumn();
    }

}
