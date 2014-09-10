package ph.txtdis.fx.button;

import javafx.scene.control.Button;
import ph.txtdis.fx.util.FX;

public abstract class FontButton<E> {
    protected final Button button;

    public FontButton(String unicode, String tooltip) {
        this(unicode, tooltip, null);
    }

    public FontButton(String unicode, String tooltip, String color) {
        this(unicode, tooltip, color, 24);
    }

    public FontButton(String unicode, String tooltip, int size) {
        this(unicode, tooltip, null, size);
    }

    public FontButton(String unicode, String tooltip, String color, int size) {
        button = new Button(unicode);
        button.setTooltip(FX.tip(tooltip));
        button.setStyle("-fx-font: " + size + " 'txtdis'; " + setColor(color) + "-fx-padding: 0; " + setSize(size));
    }

    private String setSize(int size) {
        int s = size * 2 - 2;
        return "-fx-min-width: " + s + "; -fx-max-width: " + s + "; -fx-min-height: " + s + "; -fx-max-height: " + s
                + ";";
    }

    private String setColor(String color) {
        return color == null ? "" : "-fx-text-fill: " + color + "; ";
    }

    public Button getButton() {
        return button;
    }
}
