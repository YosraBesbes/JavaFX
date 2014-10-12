package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.ProgressDialog;

public class BackupButton extends FontButton<Object> {

    public <C> BackupButton(Stage stage) {
        super("\ue804", "Backup", 44);
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
