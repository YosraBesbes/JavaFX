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
    public String toString() {
        return family + ": " + parent;
    }
}
