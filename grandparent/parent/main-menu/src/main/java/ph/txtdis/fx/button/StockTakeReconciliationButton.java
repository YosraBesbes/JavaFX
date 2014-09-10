package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.ProgressDialog;

public class StockTakeReconciliationButton extends FontButton<Object> {

    public <C> StockTakeReconciliationButton(Stage stage) {
        super("\ue82a", "Stock Take\nReconciliation", 44);
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
