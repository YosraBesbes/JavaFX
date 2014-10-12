package ph.txtdis.fx.display;

import java.math.BigDecimal;

import javafx.scene.control.TextField;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;
import ph.txtdis.util.DIS;

public class DecimalDisplay extends TextField implements TextStyled {

    public DecimalDisplay() {
        this.setEditable(false);
        this.focusTraversableProperty().set(false);
        setMaxWidth(80);
    }

    public DecimalDisplay(BigDecimal value) {
        this();
        setValue(value);
    }

    public BigDecimal getValue() {
        return DIS.parseBigDecimal(getText());
    }

    public void setValue(BigDecimal value) {
        FX.styleDecimal(this, value);
    }
}