package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InvoiceBooklet extends AbstractAudited {

    private static final long serialVersionUID = 6045289585003677813L;

    @Column(nullable = false, unique = true)
    private int startId;

    @Column(nullable = false, unique = true)
    private int endId;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Users issuedTo;

    @Override
    public String toString() {
        return startId + "-" + endId + ": " + issuedTo + " on " + getCreatedDate();
    }
}
