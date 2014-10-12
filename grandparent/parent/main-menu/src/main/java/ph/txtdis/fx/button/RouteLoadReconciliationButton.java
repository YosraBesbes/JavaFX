package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.ProgressDialog;

public class RouteLoadReconciliationButton extends FontButton<Object> {

    public <C> RouteLoadReconciliationButton(Stage stage) {
        super("\ue832", "Load-in/out\nReconciliation", 44);
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
