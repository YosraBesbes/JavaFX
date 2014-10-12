package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.ProgressDialog;

public class GroupButton extends FontButton<Object> {

    public <C> GroupButton(Stage stage) {
        super("\ue82c", "User Group", 44);
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
