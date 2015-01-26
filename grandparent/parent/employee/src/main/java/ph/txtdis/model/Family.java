package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import ph.txtdis.type.FamilyType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Family extends AbstractAudited {

    private static final long serialVersionUID = -5822699684522028625L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    private FamilyType type;

    @Column(length = 16, nullable = false)
    private String surname;

    @Column(length = 24, nullable = false)
    private String name;

    @Column(length = 1, nullable = false)
    private String middleInitial;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate birthdate;

    @Column(length = 16, nullable = false)
    private String institution;

    @Column(length = 24, nullable = false)
    private String designation;

    @Override
    public String toString() {
        return surname + ", " + name + " " + middleInitial + ".";
    }
}
