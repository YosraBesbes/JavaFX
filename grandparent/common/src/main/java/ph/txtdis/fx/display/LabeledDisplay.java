package ph.txtdis.fx.display;

import java.math.BigDecimal;
import java.time.LocalDate;

import ph.txtdis.fx.input.LabeledTextField;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public class LabeledDisplay extends LabeledTextField<String> {

    public LabeledDisplay(String name, int width) {
        super(name, new StringField(width));
        textField.setEditable(false);
        textField.focusTraversableProperty().set(false);
    }

    @Override
    public String getValue() {
        return textField.getText();
    }

    public void setDate(LocalDate date) {
        setText(Util.formatDate(date));
    }

    public void setDecimal(BigDecimal number) {
        setText(DIS.formatDecimal(number));
    }

    public void setInt(int number) {
        setText(DIS.formatInt(number));
    }

    public void setLong(long number) {
        setText(DIS.formatLong(number));
    }

    public void setPhone(long number) {
        setText(DIS.formatPhone(number));
    }

    public void setText(String value) {
        textField.setText(value);
    }
}
