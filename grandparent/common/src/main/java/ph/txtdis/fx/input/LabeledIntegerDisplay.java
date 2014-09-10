package ph.txtdis.fx.input;

import ph.txtdis.util.DIS;

public class LabeledIntegerDisplay extends LabeledIntegerField {

    public LabeledIntegerDisplay(String name) {
        super(name);
        textField.setEditable(false);
        textField.focusTraversableProperty().set(false);
    }
    
    public void setInt(int number) {
        textField.setText(DIS.formatInt(number));
    }
}
