package ph.txtdis.fx.tablecell;

import java.math.BigDecimal;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.util.DIS;

public class FourPlaceFieldTableCell<S> extends TextFieldTableCell<S, BigDecimal> implements TextStyled {

    public FourPlaceFieldTableCell() {
        super(new StringConverter<BigDecimal>() {

            @Override
            public String toString(BigDecimal number) {
                return DIS.format4Place(number);
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
        if (!empty)
            DIS.format4Place(number);
    }
}
