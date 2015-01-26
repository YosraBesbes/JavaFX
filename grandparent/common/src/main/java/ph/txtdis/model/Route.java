package ph.txtdis.model;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Route extends AbstractNamed {

    private static final long serialVersionUID = -593813397375404049L;

    public Route(String name) {
        super(name);
    }
}
