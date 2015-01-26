package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
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
@Table(indexes = @Index(columnList = "loan_id"), uniqueConstraints = @UniqueConstraint(columnNames = { "loan_id",
        "paymentDate" }))
public class Payment extends AbstractAudited {

    private static final long serialVersionUID = 4346280650221114961L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Loan loan;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate paymentDate;

    @Column(nullable = false)
    private BigDecimal amount;
}
