package ph.txtdis.fx.display;

import javafx.scene.control.TextField;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;
import ph.txtdis.util.DIS;

public class IntegerDisplay extends TextField implements TextStyled {

    public IntegerDisplay() {
        this.setEditable(false);
        this.focusTraversableProperty().set(false);
        setMaxWidth(80);
    }

    public IntegerDisplay(int value) {
        this();
        setValue(value);
    }

    public int getValue() {
        return DIS.parseInt(getText());
    }

    public void setValue(int value) {
        FX.styleInt(this, value);
    }
}