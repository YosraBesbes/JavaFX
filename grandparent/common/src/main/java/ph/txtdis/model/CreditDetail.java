package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditDetail extends AbstractAudited {

    private static final long serialVersionUID = 1655742390812653142L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer customer;

    private int term, gracePeriod;

    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal creditLimit;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @Override
    public String toString() {
        return customer + ": max of " + term + " days or P" + creditLimit + " starting " + startDate;
    }
}
