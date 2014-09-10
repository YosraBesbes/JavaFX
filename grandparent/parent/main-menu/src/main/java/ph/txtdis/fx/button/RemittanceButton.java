package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.RemittanceAppImpl;

public class RemittanceButton extends FontButton<Object> {

    public <C> RemittanceButton(Stage stage) {
        super("\ue81f", "Remittance", 44);
        button.setOnAction(event -> new RemittanceAppImpl().start());
    }
}
