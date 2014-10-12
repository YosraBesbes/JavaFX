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
public class LoadSettlementAdjustment extends AbstractAudited {

    private static final long serialVersionUID = 6040269414390198739L;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate pickDate;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Truck truck;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @Column(precision = 10, scale = 4)
    private BigDecimal qty;

    @Column(nullable = false)
    private String actionTaken;

    protected LoadSettlementAdjustment() {
    }

    public LoadSettlementAdjustment(LocalDate pickDate, Truck truck, Item item, BigDecimal qty, String actionTaken) {
        this.pickDate = pickDate;
        this.truck = truck;
        this.item = item;
        this.qty = qty;
        this.actionTaken = actionTaken;
    }

    public LocalDate getPickDate() {
        return pickDate;
    }

    public void setPickDate(LocalDate pickDate) {
        this.pickDate = pickDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public BigDecimal getQty() {
        return qty == null ? BigDecimal.ZERO : qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((actionTaken == null) ? 0 : actionTaken.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((pickDate == null) ? 0 : pickDate.hashCode());
        result = prime * result + ((truck == null) ? 0 : truck.hashCode());
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
        LoadSettlementAdjustment other = (LoadSettlementAdjustment) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (actionTaken == null) {
            if (other.actionTaken != null)
                return false;
        } else if (!actionTaken.equals(other.actionTaken))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (pickDate == null) {
            if (other.pickDate != null)
                return false;
        } else if (!pickDate.equals(other.pickDate))
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
