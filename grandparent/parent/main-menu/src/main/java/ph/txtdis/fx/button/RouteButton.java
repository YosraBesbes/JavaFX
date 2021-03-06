package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.RouteAppImpl;

public class RouteButton extends FontButton<Object> {

    public <C> RouteButton(Stage stage) {
        super("\ue822", "Route Master", 44);
        button.setOnAction(event -> new RouteAppImpl().start());
    }
}
