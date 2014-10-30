package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class LocationTree extends AbstractAudited {

    private static final long serialVersionUID = 7452722249369420815L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Location location;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Location parent;

    protected LocationTree() {
    }

    public LocationTree(Location location, Location parent) {
        this.location = location;
        this.parent = parent;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getParent() {
        return parent;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((location == null) ? 0 : location.hashCode());
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
        LocationTree other = (LocationTree) obj;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
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
        return location + ": " + parent;
    }
}
