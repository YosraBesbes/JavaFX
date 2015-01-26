package ph.txtdis.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Purchasing extends AbstractOrder<PurchasingDetail> implements Cancel, Mail, Send, Receive {

    private static final long serialVersionUID = -5606817850562768621L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected Users cancelledBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime cancelledOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected Users mailedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime mailedOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected Users sentBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime sentOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected Users receivedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime receivedOn;

    @OneToMany(mappedBy = "purchasing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchasingDetail> details;

    public Purchasing(Customer partner, LocalDate orderDate) {
        this.partner = partner;
        this.orderDate = orderDate;
    }

    @Override
    public Users getCancelledBy() {
        return cancelledBy;
    }

    @Override
    public void setCancelledBy(Users cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    @Override
    public String toString() {
        return getPartner() + " on " + getOrderDate();
    }
}
