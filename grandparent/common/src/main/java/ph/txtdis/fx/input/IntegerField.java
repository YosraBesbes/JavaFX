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

    public IntegerField() {
        this(8.1);
    }

    public IntegerField(int integer) {
        this(8.0);
        setInt(integer);
    }

    public IntegerField(long number) {
        this(11.1);
        setLong(number);
    }

    public IntegerField(double length) {
        StringProperty restrict = new SimpleStringProperty("[0-9-]");
        textProperty().addListener(new ChangeListener<String>() {
            private boolean ignore;

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (ignore || newValue == null)
                    return;

                if (!newValue.matches(restrict.get() + "*")
                        || ((length != 8.0 || newValue.length() > 1) && newValue.endsWith("-"))) {
                    ignore = true;
                    setText(oldValue);
                    ignore = false;
                } else if (newValue.length() > length) {
                    ignore = true;
                    setText(newValue.substring(0, (int) length));
                    ignore = false;
                } else {
                    setText(newValue);
                }
            }
        });

        setMaxWidth(length * 12);
        setPrefWidth(length * 12);
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