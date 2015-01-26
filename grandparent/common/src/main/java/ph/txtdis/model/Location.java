package ph.txtdis.model;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ph.txtdis.type.LocationType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Location extends AbstractNamed {

    private static final long serialVersionUID = -9066269489440543721L;

    private LocationType type;

    public Location(String name, LocationType type) {
        super(name);
        this.type = type;
    }
}
