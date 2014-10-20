package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.ItemTreeAppImpl;

public class ItemTreeButton extends FontButton<Object> {

    public <C> ItemTreeButton(Stage stage) {
        super("\ue836", "Item Family Tree", 44);
        button.setOnAction(event -> new ItemTreeAppImpl().start());
    }
}
