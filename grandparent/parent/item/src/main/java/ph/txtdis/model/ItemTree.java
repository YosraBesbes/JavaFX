package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ItemTree extends AbstractAudited {

    private static final long serialVersionUID = 4058968729625611538L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private ItemFamily family;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private ItemFamily parent;

    protected ItemTree() {
    }

    public ItemTree(ItemFamily item, ItemFamily parent) {
        this.family = item;
        this.parent = parent;
    }

    public ItemFamily getFamily() {
        return family;
    }

    public void setFamily(ItemFamily family) {
        this.family = family;
    }

    public ItemFamily getParent() {
        return parent;
    }

    public void setParent(ItemFamily parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((family == null) ? 0 : family.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
        ItemTree other = (ItemTree) obj;
        if (family == null) {
            if (other.family != null)
                return false;
        } else if (!family.equals(other.family))
            return false;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return family + ": " + parent;
    }
}
