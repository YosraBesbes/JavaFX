package ph.txtdis.fx.input;


public class StringDisplay extends StringField {

    public StringDisplay() {
    }

    public StringDisplay(String text) {
        super(text);
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
