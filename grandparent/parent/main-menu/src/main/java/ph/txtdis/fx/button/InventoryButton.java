package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.InventoryAppImpl;

public class InventoryButton extends FontButton<Object> {

    public <C> InventoryButton(Stage stage) {
        super("\ue815", "Inventory Valuation", 44);
        button.setOnAction(event -> new InventoryAppImpl().start());
    }
}
