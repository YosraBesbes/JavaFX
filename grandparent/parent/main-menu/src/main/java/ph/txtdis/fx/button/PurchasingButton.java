package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.PurchasingAppImpl;

public class PurchasingButton extends FontButton<Object> {

    public <C> PurchasingButton(Stage stage) {
        super("\ue81d", "Purchase Order", 44);
        button.setOnAction(event -> new PurchasingAppImpl().start());
    }
}
