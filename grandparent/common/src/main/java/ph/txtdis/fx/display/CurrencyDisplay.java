package ph.txtdis.fx.display;

import java.math.BigDecimal;

import javafx.scene.control.TextField;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;
import ph.txtdis.util.DIS;

public class CurrencyDisplay extends TextField implements TextStyled {

    public CurrencyDisplay() {
        this.setEditable(false);
        this.focusTraversableProperty().set(false);
        setMaxWidth(100);
    }

    public CurrencyDisplay(BigDecimal value) {
        this();
        setValue(value);
    }

    public BigDecimal getValue() {
        return DIS.parseBigDecimal(getText());
    }

    public void setValue(BigDecimal value) {
        FX.styleMonetary(this, value);
    }
}