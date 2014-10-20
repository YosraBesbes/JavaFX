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

import org.hibernate.annotations.Type;

import ph.txtdis.type.LoanType;

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
    private BigDecimal amount;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> payments;

    @Transient
    private BigDecimal payment;

    @Transient
    private BigDecimal balance;

    protected Loan() {
    }

    public Loan(Employee employee, LoanType type, LocalDate startDate, BigDecimal amount) {
        this.employee = employee;
        this.type = type;
        this.startDate = startDate;
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getAmount() {
        return amount == null ? BigDecimal.ZERO : amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Payment> getPayments() {
        return payments == null ? new ArrayList<>() : payments;
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

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getBalance() {
        return getAmount().subtract(getPayment());
    }

    public void setBalance() {
        this.balance = getBalance();
    }

    @Override
    public String toString() {
        return "[" + employee + "] " + type + " - " + startDate + ": " + amount;
    }
}
