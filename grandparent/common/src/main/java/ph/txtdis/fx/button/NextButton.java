package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DTO;
import ph.txtdis.dto.Spun;
import ph.txtdis.fx.dialog.ProgressDialog;

public class NextButton<E, K> extends FontButton<E> {

    public NextButton(Apped app, DTO<E, K> dto) {
        super("\ue81a", "Next...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    ((Spun) dto).next();
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
