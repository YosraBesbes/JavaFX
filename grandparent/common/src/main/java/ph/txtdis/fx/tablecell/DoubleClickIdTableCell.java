package ph.txtdis.fx.tablecell;

import javafx.geometry.Pos;
import javafx.stage.Stage;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;

public class DoubleClickIdTableCell<E> extends DoubleClickTableCell<E, Integer> implements TextStyled {

    public DoubleClickIdTableCell(Stage stage, Audited<E> dto) {
        super(stage, dto);
    }

    public DoubleClickIdTableCell(Stage stage) {
        super(stage, Pos.CENTER_RIGHT);
    }

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null)
            FX.styleId(this, item);
    }
}
