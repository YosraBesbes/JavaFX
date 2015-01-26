package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemTree extends AbstractAudited {

    private static final long serialVersionUID = 4058968729625611538L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private ItemFamily family;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private ItemFamily parent;

    @Override
    public String toString() {
        return family + ": " + parent;
    }
}
