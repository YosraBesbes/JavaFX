package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.Spun;
import ph.txtdis.fx.dialog.ProgressDialog;

public class BackButton extends FontButton<Object> {

    public <C> BackButton(Apped app, Spun dto) {
        super("\ue803", "Back...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    dto.back();
                }

                @Override
                protected void next() {
                    app.refresh();
                    app.setFocus();
                }
            };
        });
    }
}
