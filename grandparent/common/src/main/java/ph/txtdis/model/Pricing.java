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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((channelLimit == null) ? 0 : channelLimit.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pricing other = (Pricing) obj;
        if (channelLimit == null) {
            if (other.channelLimit != null)
                return false;
        } else if (!channelLimit.equals(other.channelLimit))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return item + " - " + type + ": ₱" + price + " starting " + startDate;
    }
}
