package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import ph.txtdis.util.DIS;

@Entity
public class StockTakeAdjustment extends AbstractAudited {

    private static final long serialVersionUID = -1676592025325317933L;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate stockTakeDate;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Quality quality;

    @Column(precision = 10, scale = 4)
    private BigDecimal qty;

    @Column(nullable = false)
    private String justification;

    protected StockTakeAdjustment() {
    }

    public StockTakeAdjustment(LocalDate stockTakeDate, Item item, Quality quality, BigDecimal qty, String justification) {
        this.stockTakeDate = stockTakeDate;
        this.item = item;
        this.quality = quality;
        this.qty = qty;
        this.justification = justification;
    }

    public LocalDate getStockTakeDate() {
        return stockTakeDate;
    }

    public void setStockTakeDate(LocalDate stockTakeDate) {
        this.stockTakeDate = stockTakeDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public BigDecimal getQty() {
        return qty == null ? BigDecimal.ZERO : qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((justification == null) ? 0 : justification.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((quality == null) ? 0 : quality.hashCode());
        result = prime * result + ((stockTakeDate == null) ? 0 : stockTakeDate.hashCode());
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
        StockTakeAdjustment other = (StockTakeAdjustment) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (justification == null) {
            if (other.justification != null)
                return false;
        } else if (!justification.equals(other.justification))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (quality == null) {
            if (other.quality != null)
                return false;
        } else if (!quality.equals(other.quality))
            return false;
        if (stockTakeDate == null) {
            if (other.stockTakeDate != null)
                return false;
        } else if (!stockTakeDate.equals(other.stockTakeDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return DIS.formatQuantity(qty);
    }
}
