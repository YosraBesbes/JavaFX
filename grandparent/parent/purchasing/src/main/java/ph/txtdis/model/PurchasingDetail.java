package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ph.txtdis.type.UomType;

@Entity
public class PurchasingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = 4692138441515885681L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Purchasing purchasing;

    @Transient
    private int daysLevelBefore;

    @Transient
    private int daysLevelAfter;

    protected PurchasingDetail() {
    }

    public PurchasingDetail(Purchasing purchasing, Item item, UomType uom, BigDecimal qty) {
        this.purchasing = purchasing;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
    }

    public int getDaysLevelBefore() {
        return daysLevelBefore;
    }

    public void setDaysLevelBefore(int daysLevelBefore) {
        this.daysLevelBefore = daysLevelBefore;
    }

    public int getDaysLevelAfter() {
        return daysLevelAfter;
    }

    public void setDaysLevelAfter(int daysLevelAfter) {
        this.daysLevelAfter = daysLevelAfter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + daysLevelAfter;
        result = prime * result + daysLevelBefore;
        result = prime * result + ((purchasing == null) ? 0 : purchasing.hashCode());
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
        PurchasingDetail other = (PurchasingDetail) obj;
        if (daysLevelAfter != other.daysLevelAfter)
            return false;
        if (daysLevelBefore != other.daysLevelBefore)
            return false;
        if (purchasing == null) {
            if (other.purchasing != null)
                return false;
        } else if (!purchasing.equals(other.purchasing))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return purchasing + ": " + qty + uom + " " + (quality == null ? "GOOD" : quality) + " " + item;
    }
}
