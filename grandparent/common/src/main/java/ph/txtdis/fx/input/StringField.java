package ph.txtdis.fx.input;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import org.apache.commons.lang3.StringUtils;

import ph.txtdis.fx.util.FX;

public class StringField extends TextField {

    public StringField() {
        this(256);
    }

    public StringField(String text) {
        this(text, 256);
    }

    public StringField(String text, int maxLength) {
        this(maxLength);
        setTooltip(FX.tip("Double-click to edit;\nuse tab to traverse"));
        setText(text);
    }

    public StringField(int maxLength) {
        if (maxLength < 256) {
            setMaxWidth(maxLength);
            setPrefWidth(maxLength);
        }

        textProperty().addListener(new ChangeListener<String>() {
            private boolean ignore;
            private IntegerProperty maximumLength = new SimpleIntegerProperty(maxLength);

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (ignore || newValue == null)
                    return;
                if (newValue.length() > maximumLength.get()) {
                    ignore = true;
                    setText(newValue.substring(0, maximumLength.get()));
                    ignore = false;
                } else {
                    oldValue = newValue;
                    setText(StringUtils.upperCase(oldValue));
                }
            }
        });
    }
}