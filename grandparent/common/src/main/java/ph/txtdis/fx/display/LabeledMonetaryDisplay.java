package ph.txtdis.fx.display;

import java.math.BigDecimal;

import ph.txtdis.fx.input.DecimalField;
import ph.txtdis.fx.input.LabeledTextField;

public class LabeledMonetaryDisplay extends LabeledTextField<BigDecimal>{

	public LabeledMonetaryDisplay(String name) {
		super(name, new MonetaryDisplay());
	}

	@Override
    public BigDecimal getValue() {
        return ((DecimalField) textField).getValue();
    }

	@Override
    public void reset() {
        textField.setText("");
    }
}
