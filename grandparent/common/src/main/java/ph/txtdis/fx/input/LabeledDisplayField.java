package ph.txtdis.fx.input;

import java.math.BigDecimal;

import ph.txtdis.util.DIS;

public class LabeledDisplayField extends LabeledTextField<String> {

	public LabeledDisplayField(String name, int width) {
		super(name, new StringField(width));
		textField.setEditable(false);
		textField.focusTraversableProperty().set(false);
	}

    @Override
    public String getValue() {
        return textField.getText();
    }

    public void setText(String value) {
        textField.setText(value);
    }
    
    public void setInt(int number) {
        setText(DIS.formatInt(number));
    }
    
    public void setLong(long number) {
        setText(DIS.formatLong(number));
    }
    
    public void setDecimal(BigDecimal number) {
        setText(DIS.formatDecimal(number));
    }
    
    public void setPhone(long number) {
        setText(DIS.formatPhone(number));
    }
}
