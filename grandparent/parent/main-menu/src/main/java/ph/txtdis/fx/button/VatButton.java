package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.VatAppImpl;

public class VatButton extends FontButton<Object> {

    public <C> VatButton(Stage stage) {
        super("\ue82f", "VAT Report", 44);
        button.setOnAction(event -> new VatAppImpl().start());
    }
}
