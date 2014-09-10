package ph.txtdis.fx.button;

import javafx.stage.Stage;

public class ReturnButton extends FontButton<Object> {

    public <C> ReturnButton(Stage stage) {
        super("\ue83c", "Return Authorization", 44);
        //button.setOnAction(event -> new PickingAppImpl().start());
    }
}
