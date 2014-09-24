package ph.txtdis.fx.tablecell;

import java.math.BigDecimal;

import javafx.stage.Stage;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;

public class DoubleClickQtyTableCell<E> extends DoubleClickTableCell<E, BigDecimal> implements TextStyled{

    public DoubleClickQtyTableCell(Stage stage, Audited<E> dto) {
        super(stage, dto);
    }

    public DoubleClickQtyTableCell(Stage stage) {
        super(stage);
    }

    @Override
    protected void updateItem(BigDecimal item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty)
            FX.styleQuantity(this, item);
    }
}
