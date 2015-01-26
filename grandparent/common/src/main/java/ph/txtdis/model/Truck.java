package ph.txtdis.model;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Truck extends AbstractDisabledNamed {

    private static final long serialVersionUID = 7715000847451554321L;

    public Truck(String name) {
        super(name);
    }
}
