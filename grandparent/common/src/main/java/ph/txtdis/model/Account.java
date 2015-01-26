package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends AbstractAudited {

    private static final long serialVersionUID = -3816774251745575218L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Users user;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Route route;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @Override
    public String toString() {
        return route + ": " + user + " starting " + startDate;
    }
}
