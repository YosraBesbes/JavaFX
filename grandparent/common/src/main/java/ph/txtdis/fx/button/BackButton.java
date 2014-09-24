package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DTO;
import ph.txtdis.dto.Spun;
import ph.txtdis.fx.dialog.ProgressDialog;

public class BackButton<E, K> extends FontButton<E> {

    public <C> BackButton(Apped app, DTO<E, K> dto) {
        super("\ue803", "Back...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    ((Spun) dto).back();
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
