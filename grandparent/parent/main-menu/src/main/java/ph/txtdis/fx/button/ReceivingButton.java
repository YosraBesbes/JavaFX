package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.ReceivingAppImpl;

public class ReceivingButton extends FontButton<Object> {

    public <C> ReceivingButton(Stage stage) {
        super("\ue81e", "Receiving Report", 44);
        button.setOnAction(event -> new ReceivingAppImpl().start());
    }
}
