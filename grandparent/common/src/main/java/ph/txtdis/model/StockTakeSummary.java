package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;
import org.hibernate.annotations.Type;

import ph.txtdis.util.DIS;

@Entity
@Subselect("select d.id, s.stock_take_date, d.item_id, d.quality_id, sum(d.qty * qpu.qty) qty from stock_take s "
        + "join stock_take_detail d on s.id = d.stock_take_id "
        + "join qty_per_uom qpu on d.item_id = qpu.item_id and qpu.uom = d.uom "
        + "group by d.id, s.stock_take_date, d.item_id, d.quality_id ")
@Synchronize({ "stock_take", "stock_take_detail", "item", "quality", "qty_per_uom" })
public class StockTakeSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate stockTakeDate;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Quality quality;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal qty;

    protected StockTakeSummary() {
    }

    public StockTakeSummary(Item item, Quality quality, BigDecimal qty) {
        this.item = item;
        this.quality = quality;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public LocalDate getStockTakeDate() {
        return stockTakeDate;
    }

    public Item getItem() {
        return item;
    }

    public Quality getQuality() {
        return quality;
    }

    public BigDecimal getQty() {
        return qty;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((quality == null) ? 0 : quality.hashCode());
        result = prime * result + ((stockTakeDate == null) ? 0 : stockTakeDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StockTakeSummary other = (StockTakeSummary) obj;
        if (id != other.id)
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
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
