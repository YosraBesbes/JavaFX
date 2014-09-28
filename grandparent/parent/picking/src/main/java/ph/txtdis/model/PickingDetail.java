package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class PickingDetail extends AbstractAudited {

    private static final long serialVersionUID = -295635229976937036L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Picking picking;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Booking booking;

    @Transient
    private Integer bookingId;

    @Transient
    private String partnerName;

    @Transient
    private LocalDate bookingDate;

    protected PickingDetail() {
    }

    public PickingDetail(Picking picking, Booking booking) {
        this.picking = picking;
        this.booking = booking;
    }

    public Picking getPicking() {
        return picking;
    }

    public void setPicking(Picking picking) {
        this.picking = picking;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Integer getBookingId() {
        return booking == null ? 0 : booking.getId();
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getPartnerName() {
        return booking == null ? null : booking.getPartner().getName();
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public LocalDate getBookingDate() {
        return booking == null ? null : booking.getOrderDate();
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((booking == null) ? 0 : booking.hashCode());
        result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
        result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
        result = prime * result + ((partnerName == null) ? 0 : partnerName.hashCode());
        result = prime * result + ((picking == null) ? 0 : picking.hashCode());
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
        PickingDetail other = (PickingDetail) obj;
        if (booking == null) {
            if (other.booking != null)
                return false;
        } else if (!booking.equals(other.booking))
            return false;
        if (bookingDate == null) {
            if (other.bookingDate != null)
                return false;
        } else if (!bookingDate.equals(other.bookingDate))
            return false;
        if (bookingId == null) {
            if (other.bookingId != null)
                return false;
        } else if (!bookingId.equals(other.bookingId))
            return false;
        if (partnerName == null) {
            if (other.partnerName != null)
                return false;
        } else if (!partnerName.equals(other.partnerName))
            return false;
        if (picking == null) {
            if (other.picking != null)
                return false;
        } else if (!picking.equals(other.picking))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return picking + ": " + booking;
    }
}
