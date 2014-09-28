package ph.txtdis.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ph.txtdis.type.ItemType;

@Entity
public class Item extends AbstractAudited implements Named {

    private static final long serialVersionUID = -3012020260825126952L;

    @Column(nullable = false, length = 18, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ItemType type;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private ItemFamily family;

    private long vendorId;

    private boolean notDiscounted;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QtyPerUom> qtyPerUom;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pricing> priceHistory;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VolumeDiscount> volumeDiscounts;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bom> boms;

    public Item() {
    }

    public Item(String name, String description, ItemType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public ItemFamily getFamily() {
        return family;
    }

    public void setFamily(ItemFamily family) {
        this.family = family;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public boolean isNotDiscounted() {
        return notDiscounted;
    }

    public void setNotDiscounted(boolean notDiscounted) {
        this.notDiscounted = notDiscounted;
    }

    public List<QtyPerUom> getQtyPerUom() {
        return qtyPerUom;
    }

    public void setQtyPerUom(List<QtyPerUom> qtyPerUom) {
        this.qtyPerUom = qtyPerUom;
    }

    public List<Pricing> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(List<Pricing> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public List<VolumeDiscount> getVolumeDiscounts() {
        return volumeDiscounts;
    }

    public void setVolumeDiscounts(List<VolumeDiscount> volumeDiscounts) {
        this.volumeDiscounts = volumeDiscounts;
    }

    public List<Bom> getBoms() {
        return boms;
    }

    public void setBoms(List<Bom> boms) {
        this.boms = boms;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((boms == null) ? 0 : boms.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((family == null) ? 0 : family.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (notDiscounted ? 1231 : 1237);
        result = prime * result + ((priceHistory == null) ? 0 : priceHistory.hashCode());
        result = prime * result + ((qtyPerUom == null) ? 0 : qtyPerUom.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + (int) (vendorId ^ (vendorId >>> 32));
        result = prime * result + ((volumeDiscounts == null) ? 0 : volumeDiscounts.hashCode());
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
        Item other = (Item) obj;
        if (boms == null) {
            if (other.boms != null)
                return false;
        } else if (!boms.equals(other.boms))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (family == null) {
            if (other.family != null)
                return false;
        } else if (!family.equals(other.family))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (notDiscounted != other.notDiscounted)
            return false;
        if (priceHistory == null) {
            if (other.priceHistory != null)
                return false;
        } else if (!priceHistory.equals(other.priceHistory))
            return false;
        if (qtyPerUom == null) {
            if (other.qtyPerUom != null)
                return false;
        } else if (!qtyPerUom.equals(other.qtyPerUom))
            return false;
        if (type != other.type)
            return false;
        if (vendorId != other.vendorId)
            return false;
        if (volumeDiscounts == null) {
            if (other.volumeDiscounts != null)
                return false;
        } else if (!volumeDiscounts.equals(other.volumeDiscounts))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
