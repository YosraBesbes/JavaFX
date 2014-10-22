package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.app.Disable;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.util.Util;

public class CancelButton<E> extends FontButton<E> {

    public CancelButton(Apped app, Audited<E> dto) {
        super("\ue80a", "Cancel...");
        button.setOnAction(event -> {
            int id = dto.getId();
            E entity = dto.get();
            ((Disable) app).disable();
            app.refresh();
            new InfoDialog((Stage) app, "Successfully disabled\n" + Util.getModule(app) + " No. " + id + ": \n"
                    + entity);
            app.setFocus();
        });
    }
}
