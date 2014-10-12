package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.ProgressDialog;

public class VatButton extends FontButton<Object> {

    public <C> VatButton(Stage stage) {
        super("\ue82f", "VAT Report", 44);
        button.setOnAction(event -> {
            new ProgressDialog(stage) {
                @Override
                protected void begin() {
                }

                @Override
                protected void next() {
                }
            };
        });
    }
}
