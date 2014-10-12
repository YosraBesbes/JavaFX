package ph.txtdis.fx.display;

import javafx.scene.control.TextField;

public class StringDisplay extends TextField {

    public StringDisplay() {
        this(256);
    }

    public StringDisplay(String text) {
        this(text, 256);
    }

    public StringDisplay(String text, int maxLength) {
        this(maxLength);
        setText(text);
    }

    public StringDisplay(int maxLength) {
        if (maxLength < 256)
            setMaxWidth(maxLength);
        setEditable(false);
        focusTraversableProperty().set(false);
    }
}
