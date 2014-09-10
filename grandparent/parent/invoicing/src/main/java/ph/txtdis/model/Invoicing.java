package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Invoicing extends AbstractOrder<InvoicingDetail> implements Remitted {

    private static final long serialVersionUID = 2322549783835028127L;

    @Transient
    private int bookingId;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    private Booking booking;

    @OneToMany(mappedBy = "invoicing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicingDetail> details;

    public Invoicing() {
    }

    public Invoicing(Customer partner, Booking booking, LocalDate orderDate) {
        this.partner = partner;
        this.booking = booking;
        this.orderDate = orderDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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
    public String toString() {
        return partner + " on " + orderDate;
    }
}
