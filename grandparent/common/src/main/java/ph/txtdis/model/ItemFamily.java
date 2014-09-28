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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((tier == null) ? 0 : tier.hashCode());
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
        ItemFamily other = (ItemFamily) obj;
        if (tier != other.tier)
            return false;
        return true;
    }
}
