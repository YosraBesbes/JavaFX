package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
public class Style extends AbstractAudited {

    private static final long serialVersionUID = -8394808921625740006L;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    private Users user;

    private String base, background, accent, font;
}
