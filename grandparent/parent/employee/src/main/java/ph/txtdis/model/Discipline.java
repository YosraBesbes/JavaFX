package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "incidence", "allegation" }))
public class Discipline extends AbstractAudited {

    private static final long serialVersionUID = -2655032643722025128L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate incidence;

    @Column(nullable = false)
    private String allegation;

    private String decision;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate effectivity;

    private int dayCount;

    private boolean noticeGiven;

    private boolean replyReceived;

    private boolean decisionGiven;

    public Discipline(Employee employee, LocalDate incidence, String allegation) {
        this.employee = employee;
        this.incidence = incidence;
        this.allegation = allegation;
    }
}
