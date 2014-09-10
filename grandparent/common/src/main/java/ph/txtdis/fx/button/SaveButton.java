package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DTO;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.fx.dialog.ProgressDialog;
import ph.txtdis.util.Util;

public class SaveButton<E> extends FontButton<E> {

    public SaveButton(Apped app, DTO<E> dto) {

        super("\ue823", "Save...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    app.save();
                }

                @Override
                protected void next() {
                    app.refresh();
                    new InfoDialog((Stage) app, "Successfully posted data of\n" + Util.getEntityIdAndName(app, dto));
                }
            };
        });
    }
}
