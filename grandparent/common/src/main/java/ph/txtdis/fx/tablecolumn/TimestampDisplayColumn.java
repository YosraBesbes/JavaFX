package ph.txtdis.fx.tablecolumn;

import java.time.ZonedDateTime;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.TimestampDisplayTableCell;

public class TimestampDisplayColumn<S> extends AbstractDisplayColumn<S, ZonedDateTime> {

    public TimestampDisplayColumn(Stage stage, String text, String field) {
        super(stage, text, field, 160, null);
    }

    @Override
    protected TableCell<S, ZonedDateTime> getTableCell(Stage stage, Pos pos) {
        return new TimestampDisplayTableCell<S>();
    }
}
