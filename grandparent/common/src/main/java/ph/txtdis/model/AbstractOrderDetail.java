package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import ph.txtdis.type.QualityType;
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

    protected QualityType quality;

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
    public QualityType getQuality() {
        return quality;
    }

    @Override
    public void setQuality(QualityType quality) {
        this.quality = quality;
    }
}
