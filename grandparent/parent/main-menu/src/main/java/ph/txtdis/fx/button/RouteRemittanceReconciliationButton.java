package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.ProgressDialog;

public class RouteRemittanceReconciliationButton extends FontButton<Object> {

    public <C> RouteRemittanceReconciliationButton(Stage stage) {
        super("\ue833", "Sales/Remittance\nReconciliation", 44);
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
