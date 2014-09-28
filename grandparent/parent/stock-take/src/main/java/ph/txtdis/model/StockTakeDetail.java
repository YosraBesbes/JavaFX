package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ph.txtdis.type.UomType;

@Entity
public class StockTakeDetail extends AbstractAudited {

    private static final long serialVersionUID = 4692138441515885681L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private StockTake stockTake;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @Transient
    private Integer itemId;

    @Transient
    private String itemName;

    @Transient
    private String itemDescription;

    @Column(nullable = false)
    private UomType uom;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal qty;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Quality quality;

    protected StockTakeDetail() {
    }

    public StockTakeDetail(StockTake stockTake, Item item, UomType uom, BigDecimal qty, Quality quality) {
        this.stockTake = stockTake;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.quality = quality;
    }

    public StockTake getStockTake() {
        return stockTake;
    }

    public void setStockTake(StockTake stockTake) {
        this.stockTake = stockTake;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getItemId() {
        return item.getId();
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return item.getName();
    }

    public String getItemDescription() {
        return item.getDescription();
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

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((itemDescription == null) ? 0 : itemDescription.hashCode());
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
        result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((quality == null) ? 0 : quality.hashCode());
        result = prime * result + ((stockTake == null) ? 0 : stockTake.hashCode());
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
        StockTakeDetail other = (StockTakeDetail) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (itemDescription == null) {
            if (other.itemDescription != null)
                return false;
        } else if (!itemDescription.equals(other.itemDescription))
            return false;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
            return false;
        if (itemName == null) {
            if (other.itemName != null)
                return false;
        } else if (!itemName.equals(other.itemName))
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
        if (stockTake == null) {
            if (other.stockTake != null)
                return false;
        } else if (!stockTake.equals(other.stockTake))
            return false;
        if (uom != other.uom)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return stockTake + ": " + qty + uom + " " + quality + " " + item;
    }
}
