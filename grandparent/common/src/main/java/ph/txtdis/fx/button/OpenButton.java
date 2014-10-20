package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.dialog.OpenDialog;

public class OpenButton<E> extends FontButton<E> {

    public OpenButton(Apped app, Audited<E> dto) {
        super("\ue81b", "Open...");
        button.setOnAction(event -> {
            int id = new OpenDialog<>(app.getModule(), (Stage) app, dto).getId();
            if (dto.exists(id)) {
                dto.setById(id);
                app.refresh();
                app.setFocus();
            } else {
                new ErrorDialog((Stage) app, app.getModule() + " No. " + id + "\nwas not found.");
            }
        });
    }
}
