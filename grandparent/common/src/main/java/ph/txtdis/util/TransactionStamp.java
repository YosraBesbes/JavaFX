package ph.txtdis.util;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import ph.txtdis.model.Users;

public class TransactionStamp {

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users user;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime timestamp;

    public TransactionStamp(LocalDate id, Users user, ZonedDateTime timestamp) {
        this.id = id;
        this.user = user;
        this.timestamp = timestamp;
    }

    public LocalDate getid() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return user + " on " + Util.formatZonedDateTime(timestamp);
    }
}
