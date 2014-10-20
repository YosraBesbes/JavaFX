package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.WarehouseAppImpl;

public class WarehouseButton extends FontButton<Object> {

    public <C> WarehouseButton(Stage stage) {
        super("\ue830", "Warehouse Master", 44);
        button.setOnAction(event -> new WarehouseAppImpl().start());
    }
}
