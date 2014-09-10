package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.InvoiceBookletAppImpl;

public class InvoiceBookletButton extends FontButton<Object> {

    public <C> InvoiceBookletButton(Stage stage) {
        super("\ue816", "Invoice Booklet", 44);
        button.setOnAction(event -> new InvoiceBookletAppImpl().start());
    }
}
