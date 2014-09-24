package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.fx.dialog.ProgressDialog;
import ph.txtdis.util.Util;

public class CancelButton<E> extends FontButton<E> {

    private int id;
    private E entity;

    public CancelButton(Apped app, Audited<E> dto) {
        super("\ue80a", "Cancel...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    id = dto.getId();
                    entity = dto.get();
                    // TODO dto.delete();
                }

                @Override
                protected void next() {
                    app.refresh();
                    new InfoDialog((Stage) app, "Successfully deleted data of\n" + Util.getModule(app) + " No. " + id
                            + ": \n" + entity);
                    app.setFocus();
                }
            };
        });
    }
}
