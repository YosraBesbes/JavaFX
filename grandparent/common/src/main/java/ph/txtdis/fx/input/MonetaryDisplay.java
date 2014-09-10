package ph.txtdis.fx.input;

import java.math.BigDecimal;

import ph.txtdis.fx.util.FX;
import ph.txtdis.util.DIS;
import javafx.scene.control.TextField;

public class MonetaryDisplay extends TextField implements TextStyled {

	public MonetaryDisplay() {
	    super();
		this.setEditable(false);
		this.setFocusTraversable(false);
	}

    public MonetaryDisplay(BigDecimal value) {
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