package ph.txtdis.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ph.txtdis.type.ItemType;

@Entity
public class Item extends AbstractAudited implements Typed {

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
    public String toString() {
        return name;
    }
}
