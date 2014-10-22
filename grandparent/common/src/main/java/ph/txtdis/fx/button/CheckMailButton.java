package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.mail.ApprovedByMail;

public class CheckMailButton extends FontButton<Object> {

    public CheckMailButton(ApprovedByMail app) {
        super("\ue840", "Check Mail...");
        button.setOnAction(event -> {
            try {
                app.handleCheckingResult(app.checkMail());
            } catch (Exception e) {
                new ErrorDialog((Stage) app, e.getMessage());
            } finally {
                ((Apped) app).refresh();
            }
        });
    }
}
