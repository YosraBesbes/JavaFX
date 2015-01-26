package ph.txtdis.model;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Quality extends AbstractNamed {

    private static final long serialVersionUID = -8062062383997440000L;

    public Quality(String name) {
        super(name);
    }
}
