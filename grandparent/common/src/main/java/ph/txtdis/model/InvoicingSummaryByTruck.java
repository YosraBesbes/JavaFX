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
@Subselect("select id.id, i.order_date, p.truck_id, id.item_id, id.quality_id, sum(id.qty * qpu.qty) qty from picking p "
        + "join picking_detail pd on p.id = pd.picking_id "
        + "join booking b on pd.booking_id = b.id join booking_detail bd on b.id = bd.booking_id "
        + "join invoicing i on i.order_date = p.pick_date and i.partner_id = b.partner_id "
        + "join invoicing_detail id on i.id = id.invoicing_id and id.item_id = bd.item_id "
        + "join qty_per_uom qpu on id.item_id = qpu.item_id and qpu.uom = id.uom "
        + "group by id.id, i.order_date, p.truck_id, id.item_id, id.quality_id ")
@Synchronize({ "invoicing", "invoicing_detail", "picking", "picking_detail", "booking", "booking_detail", "item",
        "quality", "qty_per_uom" })
public class InvoicingSummaryByTruck {

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

    protected InvoicingSummaryByTruck() {
    }

    public InvoicingSummaryByTruck(Item item, Quality quality, BigDecimal qty) {
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
