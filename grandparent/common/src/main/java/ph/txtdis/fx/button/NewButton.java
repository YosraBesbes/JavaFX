package ph.txtdis.fx.button;

import ph.txtdis.app.Apped;
import ph.txtdis.dto.Audited;

public class NewButton<E> extends FontButton<E> {

    public NewButton(Apped app, Audited<E> dto) {
        super("\ue800", "Add...");
        button.setOnAction(event -> {
            dto.reset();
            app.refresh();
            app.setFocus();
        });
    }
}
