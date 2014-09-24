package ph.txtdis.fx.tablecell;

import javafx.stage.Stage;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;

public class DoubleClickIdTableCell<E> extends DoubleClickTableCell<E, Integer> implements TextStyled{

    public DoubleClickIdTableCell(Stage stage, Audited<E> dto) {
        super(stage, dto);
    }

    public DoubleClickIdTableCell(Stage stage) {
        super(stage);
    }

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty)
            FX.styleId(this, item);
    }
}
