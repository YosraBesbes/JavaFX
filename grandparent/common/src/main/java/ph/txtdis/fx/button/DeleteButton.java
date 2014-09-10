package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DTO;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.fx.dialog.ProgressDialog;
import ph.txtdis.util.Util;

public class DeleteButton<E> extends FontButton<E> {

    private int id;
    private E entity;

    public DeleteButton(Apped app, DTO<E> dto) {
        super("\ue80a", "Delete...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    id = dto.getId();
                    entity = dto.get();
                    dto.delete();
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
