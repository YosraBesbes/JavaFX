package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Booking extends AbstractOrder<BookingDetail> {

    private static final long serialVersionUID = 7359559924256955582L;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingDetail> details;

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

    @Override
    public String toString() {
        return getPartner() + " on " + getOrderDate();
    }
}
