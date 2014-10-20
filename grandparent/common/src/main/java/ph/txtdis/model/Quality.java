package ph.txtdis.model;

import javax.persistence.Entity;

@Entity
public class Quality extends AbstractNamed {

    private static final long serialVersionUID = -8062062383997440000L;

    public Quality() {
    }

    public Quality(String name) {
        super(name);
    }
}
