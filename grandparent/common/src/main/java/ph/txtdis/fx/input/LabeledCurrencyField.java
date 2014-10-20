package ph.txtdis.fx.input;

import java.math.BigDecimal;

public class LabeledCurrencyField extends LabeledTextField<BigDecimal>{

	public LabeledCurrencyField(String name) {
		super(name, new MonetaryField());
	}

	@Override
    public BigDecimal getValue() {
        return ((DecimalField) textField).getValue();
    }

    public MonetaryField getMonetaryField() {
	    return (MonetaryField) textField;
	}
}
