package ph.txtdis.fx.tablecolumn;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import ph.txtdis.fx.tablecell.DecimalDisplayTableCell;

public class DecimalDisplayColumn<S> extends AbstractDisplayColumn<S, BigDecimal> {

    public DecimalDisplayColumn(Stage stage, String text, String fieldWithVolSuffix) {
        super(stage, text, fieldWithVolSuffix, 100, null);
    }

    @Override
    protected TableCell<S, BigDecimal> getTableCell(Stage stage, Pos pos) {
        return new DecimalDisplayTableCell<>();
    }
}
