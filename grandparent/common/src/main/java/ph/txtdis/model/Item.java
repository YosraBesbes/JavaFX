package ph.txtdis.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ph.txtdis.type.ItemType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Item extends AbstractDisabledNamed implements Disable, Named {

    private static final long serialVersionUID = -3012020260825126952L;

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

    public Item(String name, String description, ItemType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
