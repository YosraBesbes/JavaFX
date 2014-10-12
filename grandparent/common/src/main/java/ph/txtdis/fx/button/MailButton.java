package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DTO;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.mail.ApprovedByMail;
import ph.txtdis.mail.Mail;

public class MailButton<E, K> extends FontButton<E> {

    private Exception e;
    private Mail mail;

    public MailButton(Apped app, DTO<E, K> dto) {
        super("\ue80e", "Check Mail...");
        button.setOnAction(event -> {
            // new ProgressDialog((Stage) app) {
            // private Exception e;
            // private Mail mail;
            //
            // @Override
            // protected void begin() {
            try {
                mail = ((ApprovedByMail) app).checkMail();
            } catch (Exception e) {
                new ErrorDialog((Stage) app, e.getMessage());
                this.e = e;
            }
            // }
            //
            // @Override
            // protected void next() {
            if (e == null)
                ((ApprovedByMail) app).handleCheckingResult(mail);
            app.refresh();
            // }
            // };
        });
    }
}
