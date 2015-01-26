package ph.txtdis.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class AbstractNamed extends AbstractAudited implements Named {

    private static final long serialVersionUID = -5859599323217964344L;

    @Column(nullable = false, unique = true)
    protected String name;

    protected AbstractNamed(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
