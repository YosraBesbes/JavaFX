package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ph.txtdis.type.UomType;

@Entity
public class QtyPerUom extends AbstractAudited {

    private static final long serialVersionUID = 3802256527344044201L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @Column(nullable = false)
    private UomType uom;

    @Column(nullable = false, precision = 8, scale = 4)
    private BigDecimal qty;

    private boolean isPurchased, isSold, isReported;

    protected QtyPerUom() {
    }

    public QtyPerUom(Item item, UomType uom, BigDecimal qty, boolean isPurchased, boolean isSold, boolean isReported) {
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.isPurchased = isPurchased;
        this.isSold = isSold;
        this.isReported = isReported;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public UomType getUom() {
        return uom;
    }

    public void setUom(UomType uom) {
        this.uom = uom;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean isSold) {
        this.isSold = isSold;
    }

    public boolean isReported() {
        return isReported;
    }

    public void setReported(boolean isReported) {
        this.isReported = isReported;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (isPurchased ? 1231 : 1237);
        result = prime * result + (isReported ? 1231 : 1237);
        result = prime * result + (isSold ? 1231 : 1237);
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((uom == null) ? 0 : uom.hashCode());
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
        QtyPerUom other = (QtyPerUom) obj;
        if (isPurchased != other.isPurchased)
            return false;
        if (isReported != other.isReported)
            return false;
        if (isSold != other.isSold)
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (uom != other.uom)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return item + ": " + qty + " per " + uom;
    }
}
