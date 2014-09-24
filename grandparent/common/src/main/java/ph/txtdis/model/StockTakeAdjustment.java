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
    public String toString() {
        return DIS.formatQuantity(qty);
    }
}
