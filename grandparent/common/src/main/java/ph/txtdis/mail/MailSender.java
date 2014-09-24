package ph.txtdis.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import ph.txtdis.model.SystemUser;

public class MailSender {

    private final String fromEmail;
    private final MimeMessage message;

    public MailSender(final SystemUser mailOwner, final String module, final String primaryKey, final String[] toEmails)
            throws Exception {

        fromEmail = mailOwner.getEmail();
        final String password = mailOwner.getPassword();
        final String subject = "For approval: " + module + " " + primaryKey;
        final String body = "Send decision via a reply with the subject: " + module + " " + primaryKey
                + " is approved/disapproved.";
        final String file = System.getProperty("user.home") + "\\Desktop\\" + module + "."
                + primaryKey.replace("/", "-") + ".xls";

        message = new MimeMessage(Session.getInstance(setProperties(), setAuthenticator(password)));
        generateAndSendEmail(toEmails, subject, body, file);
    }

    private Properties setProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return properties;
    }

    private Authenticator setAuthenticator(final String password) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
    }

    public void generateAndSendEmail(String[] toEmails, String subject, String body, String file) throws Exception {
        setMimeMessageAddresses(toEmails);
        setMessageContent(subject, body, file);
        Transport.send(message);
    }

    private void setMimeMessageAddresses(String[] toEmails) throws Exception {
        message.setFrom(new InternetAddress(fromEmail, "txtDIS Magnum EDSA Del Monte"));
        message.setReplyTo(InternetAddress.parse(fromEmail, false));
        message.setRecipients(Message.RecipientType.TO, getAddresses(toEmails));
        message.setSentDate(new Date());
    }

    private InternetAddress[] getAddresses(String[] toEmails) throws Exception {
        int size = toEmails.length;
        InternetAddress[] addresses = new InternetAddress[size];
        for (int i = 0; i < size; i++)
            addresses[i] = new InternetAddress(toEmails[i]);
        return addresses;
    }

    private void setMessageContent(String subject, String body, String file) throws Exception {
        message.setSubject(subject);
        message.setContent(setMultipart(setMessageBody(body), setAttachment(file)));
    }

    private BodyPart setMessageBody(String body) throws MessagingException {
        BodyPart msgBody = new MimeBodyPart();
        msgBody.setText(body);
        return msgBody;
    }

    private Multipart setMultipart(BodyPart bodyPart, MimeBodyPart attachment) throws Exception {
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        multipart.addBodyPart(attachment);
        return multipart;
    }

    private MimeBodyPart setAttachment(String file) throws Exception {
        MimeBodyPart attachment = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        attachment.setDataHandler(new DataHandler(source));
        attachment.setFileName(file);
        return attachment;
    }

    public static void main(String args[]) {
        try {
            SystemUser user = new SystemUser("TXTDIS", "txtDIS@1", true);
            user.setEmail("txtdis.mgdc.edsa.dmpi@gmail.com");
            new MailSender(user, "Stock Take Reconciliation", "9/1/2014", new String[] { "txtdis.erp@gmail.com" });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
