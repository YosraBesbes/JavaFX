package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.CustomerAppImpl;

public class CustomerButton extends FontButton<Object> {

    public <C> CustomerButton(Stage stage) {
        super("\ue809", "Customer Master", 44);
        button.setOnAction(event -> new CustomerAppImpl().start());
    }
}
