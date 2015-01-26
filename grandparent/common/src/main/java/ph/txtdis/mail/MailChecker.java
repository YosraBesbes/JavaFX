package ph.txtdis.mail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import ph.txtdis.model.Users;

public class MailChecker {
    private Boolean isApproved;
    private String approverAddress;
    private ZonedDateTime approvalTimestamp;

    public MailChecker(Users mailOwner, String module, String primaryKey, LocalDate sentDate, String... approvers)
            throws MessagingException {

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getInstance(props, null);
        Store store = session.getStore();
        store.connect("imap.gmail.com", mailOwner.getEmail(), mailOwner.getPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        for (int i = inbox.getMessageCount(); i > 0; i--) {

            Message msg = inbox.getMessage(i);
            LocalDate msgDate = msg.getSentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (msgDate.isBefore(sentDate))
                break;

            for (Address address : msg.getFrom()) {
                for (String approver : approvers) {
                    if (address.toString().contains(approver)) {
                        approverAddress = approver;
                        String subject = msg.getSubject();
                        if (subject.contains(primaryKey) && subject.contains(module)) {
                            approvalTimestamp = msg.getSentDate().toInstant().atZone(ZoneId.systemDefault());
                            setDecision(subject);
                            break;
                        }
                    }
                }
                if (isApproved != null)
                    break;
            }
            if (isApproved != null)
                break;
        }
    }

    private void setDecision(String subject) {
        if (subject.contains("disapproved"))
            isApproved = false;
        else if (subject.contains("approved"))
            isApproved = true;
    }

    public Mail getMail() {
        return new Mail(approverAddress, approvalTimestamp, isApproved);
    }

    public static void main(String[] args) {
        try {
            Users user = new Users("TXTDIS", "txtDIS@1", true);
            user.setEmail("txtdis.mgdc.edsa.dmpi@gmail.com");
            Mail mail = new MailChecker(user, "Stock Take Reconciliation", "9/1/2014", LocalDate.of(2013, 9, 1),
                    "butchlim888@yahoo.com").getMail();
            System.out.println(mail.getAddress() + ", " + mail.getTimestamp() + ": " + mail.isApproved());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
