package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Invoicing extends AbstractOrder<InvoicingDetail> {

    private static final long serialVersionUID = -9082525867532466618L;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    private Booking booking;

    @OneToMany(mappedBy = "invoicing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicingDetail> details;

    @OneToMany(mappedBy = "invoicing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RemittanceDetail> remittances;

    public Invoicing(Customer partner, Booking booking, LocalDate orderDate) {
        this.partner = partner;
        this.booking = booking;
        this.orderDate = orderDate;
    }

    public int getBookingId() {
        return booking == null ? 0 : booking.getId();
    }

    @Override
    public String toString() {
        return partner + " on " + orderDate;
    }
}
