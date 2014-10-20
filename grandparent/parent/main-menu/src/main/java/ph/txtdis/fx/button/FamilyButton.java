package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.ItemFamilyAppImpl;

public class FamilyButton extends FontButton<Object> {

    public <C> FamilyButton(Stage stage) {
        super("\ue818", "Item Family", 44);
        button.setOnAction(event -> new ItemFamilyAppImpl().start());
    }
}
