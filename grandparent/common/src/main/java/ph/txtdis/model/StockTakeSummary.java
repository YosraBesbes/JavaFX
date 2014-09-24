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
    public String toString() {
        return DIS.formatQuantity(qty);
    }
}
