package ph.txtdis.fx.tablecell;

import java.math.BigDecimal;

import javafx.geometry.Pos;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;

public class DecimalDisplayTableCell<E> extends DisplayTableCell<E, BigDecimal> implements TextStyled {

    public DecimalDisplayTableCell() {
        super(Pos.CENTER_RIGHT);
    }

    @Override
    protected void updateItem(BigDecimal item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null)
            FX.styleDecimal(this, item);
    }
}
