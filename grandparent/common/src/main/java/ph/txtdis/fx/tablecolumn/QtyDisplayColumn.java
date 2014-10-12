package ph.txtdis.fx.tablecolumn;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.QtyDisplayTableCell;

public class QtyDisplayColumn<S> extends AbstractDisplayColumn<S, BigDecimal> {

    public QtyDisplayColumn(Stage stage, String text, String field, int minWidth) {
        super(stage, text, field, minWidth, null);
    }

    @Override
    protected TableCell<S, BigDecimal> getTableCell(Stage stage, Pos pos) {
        return new QtyDisplayTableCell<>();
    }
}
