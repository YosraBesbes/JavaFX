package ph.txtdis.fx.tablecolumn;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.CurrencyDisplayTableCell;

public class CurrencyDisplayColumn<S> extends AbstractDisplayColumn<S, BigDecimal> {

    public CurrencyDisplayColumn(Stage stage, String text, String fieldWithValueSuffix) {
        super(stage, text, fieldWithValueSuffix, 100, null);
    }

    @Override
    protected TableCell<S, BigDecimal> getTableCell(Stage stage, Pos pos) {
        return new CurrencyDisplayTableCell<>();
    }
}
