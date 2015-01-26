package ph.txtdis.model;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Warehouse extends AbstractNamed {

    private static final long serialVersionUID = -902162181604587549L;

    public Warehouse(String name) {
        super(name);
    }
}
