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
public class InvoicedVolumeByTruckView {

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

    protected InvoicedVolumeByTruckView() {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((quality == null) ? 0 : quality.hashCode());
        result = prime * result + ((truck == null) ? 0 : truck.hashCode());
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
        InvoicedVolumeByTruckView other = (InvoicedVolumeByTruckView) obj;
        if (id != other.id)
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (orderDate == null) {
            if (other.orderDate != null)
                return false;
        } else if (!orderDate.equals(other.orderDate))
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
        if (truck == null) {
            if (other.truck != null)
                return false;
        } else if (!truck.equals(other.truck))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return DIS.formatQuantity(qty);
    }
}
