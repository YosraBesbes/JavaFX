package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.TruckAppImpl;

public class TruckButton extends FontButton<Object> {

    public <C> TruckButton(Stage stage) {
        super("\ue838", "Truck Master", 44);
        button.setOnAction(event -> new TruckAppImpl().start());
    }
}
