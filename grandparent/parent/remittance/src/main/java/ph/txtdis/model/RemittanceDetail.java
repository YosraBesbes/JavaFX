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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + ((partnerName == null) ? 0 : partnerName.hashCode());
        result = prime * result + ((payment == null) ? 0 : payment.hashCode());
        result = prime * result + ((referenceDate == null) ? 0 : referenceDate.hashCode());
        result = prime * result + referenceId;
        result = prime * result + ((referenceType == null) ? 0 : referenceType.hashCode());
        result = prime * result + ((remittance == null) ? 0 : remittance.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        RemittanceDetail other = (RemittanceDetail) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (balance == null) {
            if (other.balance != null)
                return false;
        } else if (!balance.equals(other.balance))
            return false;
        if (partnerName == null) {
            if (other.partnerName != null)
                return false;
        } else if (!partnerName.equals(other.partnerName))
            return false;
        if (payment == null) {
            if (other.payment != null)
                return false;
        } else if (!payment.equals(other.payment))
            return false;
        if (referenceDate == null) {
            if (other.referenceDate != null)
                return false;
        } else if (!referenceDate.equals(other.referenceDate))
            return false;
        if (referenceId != other.referenceId)
            return false;
        if (referenceType != other.referenceType)
            return false;
        if (remittance == null) {
            if (other.remittance != null)
                return false;
        } else if (!remittance.equals(other.remittance))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return remittance + ": " + referenceType + " No. " + DIS.formatId(getReferenceId());
    }
}
