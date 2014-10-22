package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.mail.ApprovedByMail;

public class SendMailButton extends FontButton<Object> {

    public SendMailButton(ApprovedByMail app) {
        super("\ue80e", "Send Mail...");
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
