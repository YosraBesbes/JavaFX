package ph.txtdis.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class Purchasing extends AbstractOrder<PurchasingDetail> implements Cancel, Mail, Send, Receive {

    private static final long serialVersionUID = -5606817850562768621L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected SystemUser cancelledBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime cancelledOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected SystemUser mailedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime mailedOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected SystemUser sentBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime sentOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected SystemUser receivedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime receivedOn;

    @OneToMany(mappedBy = "purchasing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchasingDetail> details;

    public Purchasing() {
    }

    public Purchasing(Customer partner, LocalDate orderDate) {
        this.partner = partner;
        this.orderDate = orderDate;
    }

    @Override
    public List<PurchasingDetail> getDetails() {
        return details;
    }

    @Override
    public void setDetails(List<PurchasingDetail> details) {
        this.details = details;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((details == null) ? 0 : details.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Purchasing other = (Purchasing) obj;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        return true;
    }

    @Override
    public SystemUser getCancelledBy() {
        return cancelledBy;
    }

    @Override
    public void setCancelledBy(SystemUser cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    @Override
    public ZonedDateTime getCancelledOn() {
        return cancelledOn;
    }

    @Override
    public void setCancelledOn(ZonedDateTime cancelledOn) {
        this.cancelledOn = cancelledOn;
    }

    @Override
    public SystemUser getMailedBy() {
        return mailedBy;
    }

    @Override
    public void setMailedBy(SystemUser mailedBy) {
        this.mailedBy = mailedBy;
    }

    @Override
    public ZonedDateTime getMailedOn() {
        return mailedOn;
    }

    @Override
    public void setMailedOn(ZonedDateTime mailedOn) {
        this.mailedOn = mailedOn;
    }

    @Override
    public SystemUser getSentBy() {
        return sentBy;
    }

    @Override
    public void setSentBy(SystemUser sentBy) {
        this.sentBy = sentBy;
    }

    @Override
    public ZonedDateTime getSentOn() {
        return sentOn;
    }

    @Override
    public void setSentOn(ZonedDateTime sentOn) {
        this.sentOn = sentOn;
    }

    @Override
    public SystemUser getReceivedBy() {
        return receivedBy;
    }

    @Override
    public void setReceivedBy(SystemUser receivedBy) {
        this.receivedBy = receivedBy;
    }

    @Override
    public ZonedDateTime getReceivedOn() {
        return receivedOn;
    }

    @Override
    public void setReceivedOn(ZonedDateTime receivedOn) {
        this.receivedOn = receivedOn;
    }

    @Override
    public String toString() {
        return getPartner() + " on " + getOrderDate();
    }
}
