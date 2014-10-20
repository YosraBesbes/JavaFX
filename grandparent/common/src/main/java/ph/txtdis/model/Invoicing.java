package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Invoicing extends AbstractOrder<InvoicingDetail> {

    private static final long serialVersionUID = -9082525867532466618L;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    private Booking booking;

    @OneToMany(mappedBy = "invoicing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicingDetail> details;

    @OneToMany(mappedBy = "invoicing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RemittanceDetail> remittances;

    public Invoicing() {
    }

    public Invoicing(Customer partner, Booking booking, LocalDate orderDate) {
        this.partner = partner;
        this.booking = booking;
        this.orderDate = orderDate;
    }

    public int getBookingId() {
        return booking == null ? 0 : booking.getId();
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public List<InvoicingDetail> getDetails() {
        return details;
    }

    @Override
    public void setDetails(List<InvoicingDetail> details) {
        this.details = details;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((booking == null) ? 0 : booking.hashCode());
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
        Invoicing other = (Invoicing) obj;
        if (booking == null) {
            if (other.booking != null)
                return false;
        } else if (!booking.equals(other.booking))
            return false;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return partner + " on " + orderDate;
    }
}
