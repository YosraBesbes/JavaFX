package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookingDiscount extends AbstractAudited implements CustomerDiscounted {

    private static final long serialVersionUID = -7775521722611121356L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Booking booking;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal perCent;

    @Column(nullable = false)
    protected BigDecimal value;

    @Override
    public String toString() {
        return booking + ": less " + perCent + "%, valued " + value;
    }
}
