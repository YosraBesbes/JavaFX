package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class LocationTree extends AbstractAudited {

    private static final long serialVersionUID = 7452722249369420815L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Location location;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Location parent;

    @Override
    public String toString() {
        return location + ": " + parent;
    }
}
