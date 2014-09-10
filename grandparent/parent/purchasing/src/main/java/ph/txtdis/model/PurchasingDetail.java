package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ph.txtdis.type.QualityType;
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
    public String toString() {
        return purchasing + ": " + qty + uom + " " + (quality == null ? QualityType.GOOD : quality) + " " + item;
    }
}
