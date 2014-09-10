package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DTO;
import ph.txtdis.dto.SpunDTO;
import ph.txtdis.fx.dialog.ProgressDialog;

public class BackButton<E> extends FontButton<E> {

    public <C> BackButton(Apped app, DTO<E> dto) {
        super("\ue803", "Back...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    ((SpunDTO) dto).back();
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
