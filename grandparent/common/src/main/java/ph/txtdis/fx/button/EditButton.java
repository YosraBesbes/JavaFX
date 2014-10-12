package ph.txtdis.fx.button;

import ph.txtdis.app.Apped;
import ph.txtdis.dto.Reorder;

public class EditButton<E> extends FontButton<E> {

    public EditButton(Reorder app) {
        super("\ue80d", "Re-use...");
        button.setOnAction(event -> {
            app.reorder();
            ((Apped) app).refresh();
        });
    }
}
