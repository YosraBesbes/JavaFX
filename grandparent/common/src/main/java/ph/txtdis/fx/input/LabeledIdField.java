package ph.txtdis.fx.input;

import ph.txtdis.util.DIS;

public class LabeledIdField extends LabeledTextField<Integer> {

    public LabeledIdField(String name) {
        super(name, new IdField());
    }

    @Override
    public Integer getValue() {
        return DIS.parseInt(getText());
    }
}
