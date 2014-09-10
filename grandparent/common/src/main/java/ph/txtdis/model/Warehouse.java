package ph.txtdis.model;

import javax.persistence.Entity;

@Entity
public class Warehouse extends AbstractTyped {
    
    private static final long serialVersionUID = -902162181604587549L;

    protected Warehouse() {
    }

    public Warehouse(String name) {
        super(name);
    }
}
