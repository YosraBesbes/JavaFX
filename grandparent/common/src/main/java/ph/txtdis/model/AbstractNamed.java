package ph.txtdis.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractNamed extends AbstractAudited implements Named {

    private static final long serialVersionUID = -5859599323217964344L;

    @Column(nullable = false, unique = true)
    private String name;

    protected AbstractNamed() {
    }

    protected AbstractNamed(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
