package ph.txtdis.fx.input;

import java.math.BigDecimal;

import ph.txtdis.fx.util.FX;

public class MonetaryField extends DecimalField {

    public MonetaryField() {
        super();
    }

    public MonetaryField(BigDecimal value) {
        super(value);
    }

    @Override
    public void setValue(BigDecimal number) {
        FX.styleMonetary(this, number);
    }
}
