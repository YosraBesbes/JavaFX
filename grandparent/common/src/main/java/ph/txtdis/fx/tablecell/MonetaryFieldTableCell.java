package ph.txtdis.fx.tablecell;

import java.math.BigDecimal;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;
import ph.txtdis.util.DIS;

public class MonetaryFieldTableCell<S> extends TextFieldTableCell<S, BigDecimal> implements TextStyled {

    public MonetaryFieldTableCell() {
        super(new StringConverter<BigDecimal>() {

            @Override
            public String toString(BigDecimal number) {
                return DIS.formatMonetary(number);
            }

            @Override
            public BigDecimal fromString(String text) {
                return DIS.parseBigDecimal(text);
            }
        });
    }

    @Override
    public void updateItem(BigDecimal number, boolean empty) {
        super.updateItem(number, empty);
        if (number != null)
            FX.styleMonetary(this, number);
    }
}
