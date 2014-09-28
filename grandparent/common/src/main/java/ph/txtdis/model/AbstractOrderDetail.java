package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import ph.txtdis.type.UomType;

@MappedSuperclass
public abstract class AbstractOrderDetail extends AbstractAudited implements ItemDetailed {

    private static final long serialVersionUID = 1774123388619693560L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    protected Item item;

    @Column(nullable = false)
    protected UomType uom;

    @Column(nullable = false, precision = 10, scale = 4)
    protected BigDecimal qty;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected VolumeDiscount discount;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    protected Quality quality;

    protected AbstractOrderDetail() {
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int getItemId() {
        return item.getId();
    }

    @Override
    public String getItemName() {
        return item.getName();
    }

    @Override
    public String getItemDescription() {
        return item.getDescription();
    }

    @Override
    public UomType getUom() {
        return uom;
    }

    @Override
    public void setUom(UomType uom) {
        this.uom = uom;
    }

    @Override
    public BigDecimal getQty() {
        return qty;
    }

    @Override
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public VolumeDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(VolumeDiscount discount) {
        this.discount = discount;
    }

    @Override
    public Quality getQuality() {
        return quality;
    }

    @Override
    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((quality == null) ? 0 : quality.hashCode());
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
        AbstractOrderDetail other = (AbstractOrderDetail) obj;
        if (discount == null) {
            if (other.discount != null)
                return false;
        } else if (!discount.equals(other.discount))
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
        if (quality == null) {
            if (other.quality != null)
                return false;
        } else if (!quality.equals(other.quality))
            return false;
        if (uom != other.uom)
            return false;
        return true;
    }
}
