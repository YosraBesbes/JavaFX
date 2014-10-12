package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.InvoicingAppImpl;

public class InvoicingButton extends FontButton<Object> {

    public <C> InvoicingButton(Stage stage) {
        super("\ue817", "Sales Invoice", 44);
        button.setOnAction(event -> new InvoicingAppImpl().start());
    }
}
