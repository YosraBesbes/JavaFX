package ph.txtdis.model;

import javax.persistence.Entity;

import ph.txtdis.type.LocationType;

@Entity
public class Location extends AbstractTyped {

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
}
