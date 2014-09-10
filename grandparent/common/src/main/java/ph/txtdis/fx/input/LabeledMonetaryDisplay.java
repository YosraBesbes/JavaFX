package ph.txtdis.fx.input;

import java.math.BigDecimal;

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
