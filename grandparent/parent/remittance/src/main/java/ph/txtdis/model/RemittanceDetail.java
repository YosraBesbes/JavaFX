package ph.txtdis.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ph.txtdis.type.RemittanceReferenceType;
import ph.txtdis.util.DIS;

@Entity
public class RemittanceDetail extends AbstractAudited {

    private static final long serialVersionUID = -5523462515012613316L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Remittance remittance;

    @Column(nullable = false)
    private RemittanceReferenceType referenceType;

    @Column(nullable = false)
    private int referenceId;

    @Column(nullable = false)
    private String partnerName;

    @Column(nullable = false)
    private Date referenceDate;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal payment;

    @Transient
    private BigDecimal balance;

    protected RemittanceDetail() {
    }

    public RemittanceDetail(Remittance remittance, RemittanceReferenceType referenceType, int referenceId,
            String partnerName, Date referenceDate, BigDecimal amount, BigDecimal payment) {
        this.remittance = remittance;
        this.referenceType = referenceType;
        this.referenceId = referenceId;
        this.partnerName = partnerName;
        this.referenceDate = referenceDate;
        this.amount = amount;
        this.payment = payment;
    }

    public Remittance getRemittance() {
        return remittance;
    }

    public void setRemittance(Remittance remittance) {
        this.remittance = remittance;
    }

    public RemittanceReferenceType getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(RemittanceReferenceType referenceType) {
        this.referenceType = referenceType;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPayment() {
        return payment == null ? BigDecimal.ZERO : payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getBalance() {
        return getAmount().subtract(getPayment());
    }

    @Override
    public String toString() {
        return remittance + ": " + referenceType + " No. " + DIS.formatId(getReferenceId());
    }
}
