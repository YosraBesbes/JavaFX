package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class AbstractOrder<D extends ItemDetailed> extends AbstractAudited implements Ordered<D> {

    private static final long serialVersionUID = 3715783817464199036L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    protected Customer partner;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected Route route;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected CreditDetail credit;

    @Column(nullable = false)
    protected BigDecimal value;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    protected LocalDate orderDate;

    private String remarks;
}
