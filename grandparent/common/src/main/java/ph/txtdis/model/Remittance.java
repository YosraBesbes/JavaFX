package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import ph.txtdis.type.RemittanceType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Remittance extends AbstractAudited {

    private static final long serialVersionUID = -5860334462169889589L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer partner;

    @Column(nullable = false)
    private RemittanceType type;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate orderDate;

    private String remarks;

    @OneToMany(mappedBy = "remittance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RemittanceDetail> details;

    public Remittance(Customer partner, RemittanceType type, String reference, BigDecimal value, LocalDate orderDate) {
        this.partner = partner;
        this.type = type;
        this.reference = reference;
        this.value = value;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return partner + " on " + orderDate;
    }
}
