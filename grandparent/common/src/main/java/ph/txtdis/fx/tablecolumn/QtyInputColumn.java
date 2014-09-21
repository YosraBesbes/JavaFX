package ph.txtdis.fx.tablecolumn;

import java.math.BigDecimal;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import ph.txtdis.fx.tablecell.QuantityFieldTableCell;

public class QtyInputColumn<S> extends AbstractInputColumn<S, BigDecimal> {

    public QtyInputColumn(Stage stage, String text, String field, int minWidth) {
        super(stage, text, field, minWidth);
    }

    @Override
    protected Callback<TableColumn<S, BigDecimal>, TableCell<S, BigDecimal>> getCallback(Stage stage) {
        return column -> new QuantityFieldTableCell<S>();
    }

}
