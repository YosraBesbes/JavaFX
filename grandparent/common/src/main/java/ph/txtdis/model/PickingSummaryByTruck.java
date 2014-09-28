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
@Subselect("select bd.id, p.pick_date, p.truck_id, bd.item_id, bd.quality_id, sum(bd.qty * qpu.qty) qty from picking p "
        + "join picking_detail d on p.id = d.picking_id "
        + "join booking b on d.booking_id = b.id join booking_detail bd on b.id = bd.booking_id "
        + "join qty_per_uom qpu on bd.item_id = qpu.item_id and qpu.uom = bd.uom "
        + "group by bd.id, p.pick_date, p.truck_id, bd.item_id, bd.quality_id ")
@Synchronize({ "picking", "picking_detail", "booking", "booking_detail", "item", "quality", "qty_per_uom" })
public class PickingSummaryByTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate pickDate;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Truck truck;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Quality quality;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal qty;

    protected PickingSummaryByTruck() {
    }

    public PickingSummaryByTruck(Item item, Quality quality, BigDecimal qty) {
        this.item = item;
        this.quality = quality;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public LocalDate getPickDate() {
        return pickDate;
    }

    public Truck getTruck() {
        return truck;
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
        result = prime * result + ((pickDate == null) ? 0 : pickDate.hashCode());
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
        PickingSummaryByTruck other = (PickingSummaryByTruck) obj;
        if (id != other.id)
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (pickDate == null) {
            if (other.pickDate != null)
                return false;
        } else if (!pickDate.equals(other.pickDate))
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
