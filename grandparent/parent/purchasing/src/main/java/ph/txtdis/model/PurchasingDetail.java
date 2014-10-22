package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ph.txtdis.type.UomType;
import ph.txtdis.util.DIS;

@Entity
public class PurchasingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = 4692138441515885681L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Purchasing purchasing;

    @Transient
    private int daysLevel;

    protected PurchasingDetail() {
    }

    public PurchasingDetail(Purchasing purchasing, Item item, UomType uom, BigDecimal qty, BigDecimal price,
            Quality quality, int daysLevel) {
        this.purchasing = purchasing;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.price = price;
        this.quality = quality;
        this.daysLevel = daysLevel;
    }

    public String getDaysLevel() {
        return daysLevel > 365 ? ">365" : DIS.formatInt(daysLevel);
    }

    public void setDaysLevel(int daysLevel) {
        this.daysLevel = daysLevel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + daysLevel;
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
        if (daysLevel != other.daysLevel)
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
        return purchasing + ": " + qty + uom + " " + quality + " " + item;
    }
}
