package ph.txtdis.fx.button;

import ph.txtdis.app.Apped;
import ph.txtdis.dto.Spun;

public class NextButton extends FontButton<Object> {

    public NextButton(Apped app, Spun dto) {
        super("\ue81a", "Next...");
        button.setOnAction(event -> {
            dto.next();
            app.refresh();
            app.setFocus();
        });
    }
}
