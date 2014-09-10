package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.ItemAppImpl;

public class ItemButton extends FontButton<Object> {

    public <C> ItemButton(Stage stage) {
        super("\ue819", "Item Master", 44);
        button.setOnAction(event -> new ItemAppImpl().start());
    }
}
