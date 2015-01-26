package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import ph.txtdis.type.PricingType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "item_id", "startDate", "type", "channel_limit_id" }))
public class Pricing extends AbstractAudited {

    private static final long serialVersionUID = -5721868302278165093L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @Column(nullable = false)
    private PricingType type;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal price;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Channel channelLimit;

    public Pricing(Item item, PricingType type, BigDecimal price, LocalDate startDate) {
        this.item = item;
        this.type = type;
        this.price = price;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return item + " - " + type + ": ₱" + price + " starting " + startDate;
    }
}
