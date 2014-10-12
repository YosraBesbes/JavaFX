package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.ProgressDialog;

public class SalesButton extends FontButton<Object> {

    public <C> SalesButton(Stage stage) {
        super("\ue820", "Sales Report", 44);
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
