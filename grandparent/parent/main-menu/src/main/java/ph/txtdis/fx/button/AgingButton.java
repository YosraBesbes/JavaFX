package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.AgingAppImpl;

public class AgingButton extends FontButton<Object> {

    public <C> AgingButton(Stage stage) {
        super("\ue802", "Aging A/Rs", 44);
        button.setOnAction(event -> new AgingAppImpl().start());
    }
}
