package ph.txtdis.fx.tablecolumn;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.MonetaryDisplayTableCell;

public class MonetaryDisplayColumn<S> extends AbstractDisplayColumn<S, BigDecimal> {

    public MonetaryDisplayColumn(Stage stage, String text, String field) {
        super(stage, text, field, 100, null);
    }

    @Override
    protected TableCell<S, BigDecimal> getTableCell(Stage stage, Pos pos) {
        return new MonetaryDisplayTableCell<>();
    }
}
