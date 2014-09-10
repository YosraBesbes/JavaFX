package ph.txtdis.fx.button;

import javafx.scene.control.Button;
import ph.txtdis.fx.util.FX;

public abstract class TextButton<E> {
    protected final Button button;

    public TextButton(String name) {
        button = FX.createButton(name);
    }

    public Button getButton() {
        return button;
    }
}
