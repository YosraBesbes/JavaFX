package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.PickingAppImpl;

public class PickingButton extends FontButton<Object> {

    public <C> PickingButton(Stage stage) {
        super("\ue83b", "Picking", 44);
        button.setOnAction(event -> new PickingAppImpl().start());
    }
}
