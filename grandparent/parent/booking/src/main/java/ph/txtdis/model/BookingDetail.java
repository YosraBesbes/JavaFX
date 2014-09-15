package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ph.txtdis.type.UomType;

@Entity
public class BookingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = 3818816253461961960L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
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
    public String toString() {
        return booking + ": " + qty + uom + " " + quality + " " + item;
    }
}
