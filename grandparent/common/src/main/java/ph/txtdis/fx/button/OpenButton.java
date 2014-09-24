package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.dialog.OpenDialog;
import ph.txtdis.fx.dialog.ProgressDialog;
import ph.txtdis.fx.util.FX;

public class OpenButton<E> extends FontButton<E> {

    public OpenButton(Apped app, Audited<E> dto) {

        super("\ue81b", "Open...");
        button.setOnAction(event -> {
            String name = FX.moduleName(app);
            int id = new OpenDialog<>(name, (Stage) app, dto).getId();
            if (dto.exists(id)) {
                new ProgressDialog((Stage) app) {
                    @Override
                    protected void begin() {
                        dto.setById(id);
                    }

                    @Override
                    protected void next() {
                        app.refresh();
                        app.setFocus();
                    }
                };
            } else {
                new ErrorDialog((Stage) app, name + " No. " + id + "\nwas not found.");
            }
        });
    }
}
