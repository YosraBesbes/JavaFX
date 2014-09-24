package ph.txtdis.model;

import javax.persistence.Entity;

import ph.txtdis.type.ItemTier;

@Entity
public class ItemFamily extends AbstractNamed {

    private static final long serialVersionUID = 8557253216462098682L;

    private ItemTier tier;

    protected ItemFamily() {
    }

    public ItemFamily(String name, ItemTier tier) {
        super(name);
        this.tier = tier;
    }

    public ItemTier getTier() {
        return tier;
    }

    public void setTier(ItemTier tier) {
        this.tier = tier;
    }
}
