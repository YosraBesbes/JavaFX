package ph.txtdis.model;

import javax.persistence.Entity;

@Entity
public class Route extends AbstractTyped {

    private static final long serialVersionUID = -593813397375404049L;

    protected Route() {
    }

    public Route(String name) {
        super(name);
    }
}
