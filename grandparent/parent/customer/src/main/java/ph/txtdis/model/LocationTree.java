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
    public String toString() {
        return location + ": " + parent;
    }
}
