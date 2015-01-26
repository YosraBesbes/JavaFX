package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "startDate", "employer", "designation" }),
        name = "past_work")
public class PastWork extends AbstractAudited {

    private static final long serialVersionUID = 4972100444095311026L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate endDate;

    @Column(nullable = false)
    private String employer;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private BigDecimal lastPay;

    @Column(nullable = false)
    private String reasonForLeaving;

    @Column(nullable = false)
    private String referenceName;

    @Column(nullable = false)
    private String referenceDesignation;

    @Column(nullable = false)
    private String referencePhone;
}
