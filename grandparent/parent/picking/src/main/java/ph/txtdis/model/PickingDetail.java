package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public PickingDetail(Picking picking, Booking booking) {
        this.picking = picking;
        this.booking = booking;
    }

    public Integer getBookingId() {
        return booking == null ? 0 : booking.getId();
    }

    public String getPartnerName() {
        return booking == null ? null : booking.getPartner().getName();
    }

    public LocalDate getBookingDate() {
        return booking == null ? null : booking.getOrderDate();
    }

    @Override
    public String toString() {
        return picking + ": " + booking;
    }
}
