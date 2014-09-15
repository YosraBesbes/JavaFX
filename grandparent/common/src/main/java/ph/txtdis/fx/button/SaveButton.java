package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.AuditedDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.fx.dialog.ProgressDialog;
import ph.txtdis.util.Util;

public class SaveButton<E> extends FontButton<E> {

    public SaveButton(Apped app, AuditedDTO<E> dto) {

        super("\ue823", "Save...");

        button.setOnAction(event -> {

            new ProgressDialog((Stage) app) {
                InvalidException e;

                @Override
                protected void begin() {
                    try {
                        app.save();
                    } catch (InvalidException e) {
                        this.e = e;
                    }
                }

                @Override
                protected void next() {
                    if (e == null) {
                        app.refresh();
                        new InfoDialog((Stage) app, "Successfully posted data of\n" + Util.getEntityIdAndName(app, dto));
                    } else {
                        new ErrorDialog(stage, e.getMessage());
                    }
                }
            };
        });
    }
}
