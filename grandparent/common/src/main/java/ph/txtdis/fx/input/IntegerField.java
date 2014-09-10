package ph.txtdis.fx.input;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import ph.txtdis.fx.util.FX;
import ph.txtdis.util.DIS;

public class IntegerField extends TextField implements TextStyled {

    public IntegerField(int integer) {
        this();
        setMaxWidth(80);
        setInt(integer);
    }

    public IntegerField(long number) {
        this();
        setMaxWidth(110);
        setLong(number);
    }

    public IntegerField() {
        StringProperty restrict = new SimpleStringProperty("[0-9-]");
        textProperty().addListener(new ChangeListener<String>() {
            private boolean ignore;

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (ignore || newValue == null)
                    return;
                if (!newValue.matches(restrict.get() + "*") || (newValue.length() > 1 && newValue.endsWith("-"))) {
                    ignore = true;
                    setText(oldValue);
                    ignore = false;
                }
            }
        });
        setAlignment(Pos.TOP_RIGHT);
    }

    public int getInt() {
        return DIS.parseInt(getText());
    }

    public void setInt(int number) {
        FX.styleInt(this, number);
    }

    public long getLong() {
        return DIS.parseLong(getText());
    }

    public void setLong(long number) {
        setText(DIS.formatLong(number));
    }
}