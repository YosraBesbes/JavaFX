package ph.txtdis.fx.tablecolumn;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.DateDisplayTableCell;

public class DateDisplayColumn<S> extends AbstractDisplayColumn<S, LocalDate> {

    public DateDisplayColumn(Stage stage, String text, String field) {
        super(stage, text, field, 90, null);
    }

    @Override
    protected TableCell<S, LocalDate> getTableCell(Stage stage, Pos pos) {
        return new DateDisplayTableCell<S>();
    }
}
