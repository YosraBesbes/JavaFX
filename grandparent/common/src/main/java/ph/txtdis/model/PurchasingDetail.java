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

    private String justification;

    @Transient
    private String daysLevel;

    protected PurchasingDetail() {
    }

    public PurchasingDetail(Purchasing purchasing, Item item, UomType uom, BigDecimal qty, BigDecimal price,
            Quality quality, String daysLevel, String justification) {
        this.purchasing = purchasing;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.price = price;
        this.quality = quality;
        this.daysLevel = daysLevel;
        this.justification = justification;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getDaysLevel() {
        return daysLevel;
    }

    public void setDaysLevel(String daysLevel) {
        this.daysLevel = daysLevel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((daysLevel == null) ? 0 : daysLevel.hashCode());
        result = prime * result + ((justification == null) ? 0 : justification.hashCode());
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
        if (daysLevel == null) {
            if (other.daysLevel != null)
                return false;
        } else if (!daysLevel.equals(other.daysLevel))
            return false;
        if (justification == null) {
            if (other.justification != null)
                return false;
        } else if (!justification.equals(other.justification))
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
