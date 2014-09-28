package ph.txtdis.model;

import javax.persistence.Entity;

import ph.txtdis.type.LocationType;

@Entity
public class Location extends AbstractNamed {

    private static final long serialVersionUID = -9066269489440543721L;

    private LocationType type;

    protected Location() {
    }

    public Location(String name, LocationType type) {
        super(name);
        this.type = type;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Location other = (Location) obj;
        if (type != other.type)
            return false;
        return true;
    }
}
