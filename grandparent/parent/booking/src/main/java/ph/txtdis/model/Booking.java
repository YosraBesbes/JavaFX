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
public class Booking extends AbstractOrder<BookingDetail> {

    private static final long serialVersionUID = 7359559924256955582L;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingDetail> details;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser printedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime printedOn;

    public Booking() {
    }

    public Booking(Customer partner, LocalDate orderDate) {
        this.partner = partner;
        this.orderDate = orderDate;
    }

    @Override
    public List<BookingDetail> getDetails() {
        return details;
    }

    @Override
    public void setDetails(List<BookingDetail> details) {
        this.details = details;
    }

    public SystemUser getPrintedBy() {
        return printedBy;
    }

    public void setPrintedBy(SystemUser printedBy) {
        this.printedBy = printedBy;
    }

    public ZonedDateTime getPrintedOn() {
        return printedOn;
    }

    public void setPrintedOn(ZonedDateTime printedOn) {
        this.printedOn = printedOn;
    }

    @Override
    public String toString() {
        return getPartner() + " on " + getOrderDate();
    }
}
