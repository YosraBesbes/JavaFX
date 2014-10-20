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

import org.hibernate.annotations.Type;

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

    protected Payment() {
    }

    public Payment(Loan loan, LocalDate paymentDate, BigDecimal amount) {
        this.loan = loan;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "[" + loan + "] " + paymentDate + ": " + amount;
    }
}
