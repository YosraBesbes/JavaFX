package ph.txtdis.fx.display;

import ph.txtdis.fx.input.LabeledIntegerField;
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
