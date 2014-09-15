package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.AuditedDTO;
import ph.txtdis.dto.SpunDTO;
import ph.txtdis.fx.dialog.ProgressDialog;

public class NextButton<E> extends FontButton<E> {

    public NextButton(Apped app, AuditedDTO<E> dto) {
        super("\ue81a", "Next...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    ((SpunDTO) dto).next();
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
