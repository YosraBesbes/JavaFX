package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class CustomerDiscount extends AbstractAudited {

    private static final long serialVersionUID = -455882680349394952L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer customer;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discount;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private ItemFamily familyLimit;

    public CustomerDiscount(Customer customer, int level, BigDecimal discount, LocalDate startDate) {
        this.customer = customer;
        this.level = level;
        this.discount = discount;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return customer + ": less " + discount + "% " + familyLimit() + " starting " + startDate;
    }

    private String familyLimit() {
        return familyLimit == null ? "" : " for " + familyLimit;
    }
}
