package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ph.txtdis.type.UomType;

@Entity
public class BookingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = 3818816253461961960L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Booking booking;

    protected BookingDetail() {
    }

    public BookingDetail(Booking booking, Item item, UomType uom, BigDecimal qty, Quality quality) {
        this.booking = booking;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.quality = quality;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((booking == null) ? 0 : booking.hashCode());
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
        BookingDetail other = (BookingDetail) obj;
        if (booking == null) {
            if (other.booking != null)
                return false;
        } else if (!booking.equals(other.booking))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return booking + ": " + qty + uom + " " + quality + " " + item;
    }
}
