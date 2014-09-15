package ph.txtdis.fx.tablecolumn;

import java.math.BigDecimal;

import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.DoubleClickQtyTableCell;

public class QtyDisplayTableColumn<S> extends DisplayTableColumn<S, BigDecimal> {

    public QtyDisplayTableColumn(Stage stage, String text, String field, int minWidth) {
        super(stage, text, field, minWidth);
    }

    @Override
    protected TableCell<S, BigDecimal> getTableCell(Stage stage) {
        return new DoubleClickQtyTableCell<S>(stage);
    }
}
