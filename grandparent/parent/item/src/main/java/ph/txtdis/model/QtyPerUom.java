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
    public String toString() {
        return item + ": " + qty + " per " + uom;
    }
}
