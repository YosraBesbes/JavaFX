package ph.txtdis.model;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ph.txtdis.type.ItemTier;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class ItemFamily extends AbstractNamed {

    private static final long serialVersionUID = 8557253216462098682L;

    private ItemTier tier;

    public ItemFamily(String name, ItemTier tier) {
        super(name);
        this.tier = tier;
    }
}
