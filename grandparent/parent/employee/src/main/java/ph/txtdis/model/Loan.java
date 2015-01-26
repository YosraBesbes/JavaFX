package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import ph.txtdis.type.LoanType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "startDate", "amount" }))
public class Loan extends AbstractAudited {

    private static final long serialVersionUID = 4690591771708072167L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    private LoanType type;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> payments = new ArrayList<Payment>();

    @Transient
    private BigDecimal payment;

    @Transient
    private BigDecimal balance;

    public Loan(Employee employee, LoanType type, LocalDate startDate, BigDecimal amount) {
        this.employee = employee;
        this.type = type;
        this.startDate = startDate;
        this.amount = amount;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
        setPayment(sumPayments());
    }

    public BigDecimal getPayment() {
        setPayment(sumPayments());
        return payment;
    }

    private BigDecimal sumPayments() {
        BigDecimal total = BigDecimal.ZERO;
        for (Payment pay : getPayments())
            total = total.add(pay.getAmount());
        return total;
    }

    public BigDecimal getBalance() {
        return getAmount().subtract(getPayment());
    }

    public void setBalance() {
        this.balance = getBalance();
    }
}
