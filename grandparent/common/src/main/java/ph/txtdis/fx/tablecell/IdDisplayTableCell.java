package ph.txtdis.fx.tablecell;

import javafx.geometry.Pos;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;

public class IdDisplayTableCell<E> extends DisplayTableCell<E, Integer> implements TextStyled {

    public IdDisplayTableCell() {
        super(Pos.CENTER_RIGHT);
    }

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null)
            FX.styleId(this, item);
    }
}
