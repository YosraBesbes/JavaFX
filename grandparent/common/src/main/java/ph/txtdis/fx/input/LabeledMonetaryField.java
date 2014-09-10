package ph.txtdis.fx.input;

import java.math.BigDecimal;

public class LabeledMonetaryField extends LabeledTextField<BigDecimal>{

	public LabeledMonetaryField(String name) {
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
