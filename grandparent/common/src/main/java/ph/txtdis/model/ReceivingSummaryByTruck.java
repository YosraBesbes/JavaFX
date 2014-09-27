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
@Subselect("select rd.id, r.order_date, p.truck_id, rd.item_id, rd.quality_id, sum(rd.qty * qpu.qty) qty from picking p "
        + "join picking_detail pd on p.id = pd.picking_id "
        + "join booking b on pd.booking_id = b.id join booking_detail bd on b.id = bd.booking_id "
        + "join receiving r on r.order_date = p.pick_date and r.partner_id = b.partner_id "
        + "join receiving_detail rd on r.id = rd.receiving_id and rd.item_id = bd.item_id "
        + "join qty_per_uom qpu on rd.item_id = qpu.item_id and qpu.uom = rd.uom "
        + "group by rd.id, r.order_date, p.truck_id, rd.item_id, rd.quality_id ")
@Synchronize({ "receiving", "receiving_detail", "picking", "picking_detail", "booking", "booking_detail", "item",
        "quality", "qty_per_uom" })
public class ReceivingSummaryByTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate orderDate;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Truck truck;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Quality quality;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal qty;

    protected ReceivingSummaryByTruck() {
    }

    public ReceivingSummaryByTruck(Item item, Quality quality, BigDecimal qty) {
        this.item = item;
        this.quality = quality;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
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
