package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import ph.txtdis.type.PricingType;

@Entity
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

    public Pricing() {
    }

    public Pricing(Item item, PricingType type, BigDecimal price, LocalDate startDate) {
        this.item = item;
        this.type = type;
        this.price = price;
        this.startDate = startDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public PricingType getType() {
        return type;
    }

    public void setType(PricingType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Channel getChannelLimit() {
        return channelLimit;
    }

    public void setChannelLimit(Channel channelLimit) {
        this.channelLimit = channelLimit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return item + " - " + type + ": ₱" + price + " starting " + startDate;
    }
}
