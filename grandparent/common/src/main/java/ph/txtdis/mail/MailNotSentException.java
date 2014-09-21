package ph.txtdis.mail;

import javax.mail.MessagingException;

public class MailNotSentException extends MessagingException {

    private static final long serialVersionUID = -7607606406819447636L;

    public MailNotSentException() {
        super("Mail was not sent.\nCheck every network connections and close all other applications."
                + "\nClick the excel button to resend.");
    }
}
