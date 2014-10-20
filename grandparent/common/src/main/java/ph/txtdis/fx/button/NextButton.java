package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.Spun;
import ph.txtdis.fx.dialog.ProgressDialog;

public class NextButton extends FontButton<Object> {

    public NextButton(Apped app, Spun dto) {
        super("\ue81a", "Next...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    dto.next();
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
