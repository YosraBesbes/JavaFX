package ph.txtdis.fx.button;

import ph.txtdis.app.Apped;
import ph.txtdis.dto.Spun;

public class BackButton extends FontButton<Object> {

    public BackButton(Apped app, Spun dto) {
        super("\ue803", "Back...");
        button.setOnAction(event -> {
            dto.back();
            app.refresh();
            app.setFocus();
        });
    }
}
