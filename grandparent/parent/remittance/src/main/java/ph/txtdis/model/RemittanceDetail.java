package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class RemittanceDetail extends AbstractAudited {

    private static final long serialVersionUID = 7471155507338324274L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Remittance remittance;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Invoicing invoice;

    @Column(nullable = false)
    private BigDecimal unpaid;

    @Column(nullable = false)
    private BigDecimal payment;

    protected RemittanceDetail() {
    }

    public RemittanceDetail(Remittance remittance, Invoicing invoice, BigDecimal unpaid, BigDecimal payment) {
        this.remittance = remittance;
        this.invoice = invoice;
        this.unpaid = unpaid;
        this.payment = payment;
    }

    public Remittance getRemittance() {
        return remittance;
    }

    public int getInvoiceId() {
        return invoice.getId();
    }

    public Customer getPartner() {
        return invoice.getPartner();
    }

    public LocalDate getDate() {
        return invoice.getOrderDate();
    }

    public BigDecimal getUnpaidValue() {
        return unpaid;
    }

    public BigDecimal getPaymentValue() {
        return payment;
    }

    public BigDecimal getBalanceValue() {
        return payment.subtract(unpaid);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
        result = prime * result + ((payment == null) ? 0 : payment.hashCode());
        result = prime * result + ((remittance == null) ? 0 : remittance.hashCode());
        result = prime * result + ((unpaid == null) ? 0 : unpaid.hashCode());
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
        if (invoice == null) {
            if (other.invoice != null)
                return false;
        } else if (!invoice.equals(other.invoice))
            return false;
        if (payment == null) {
            if (other.payment != null)
                return false;
        } else if (!payment.equals(other.payment))
            return false;
        if (remittance == null) {
            if (other.remittance != null)
                return false;
        } else if (!remittance.equals(other.remittance))
            return false;
        if (unpaid == null) {
            if (other.unpaid != null)
                return false;
        } else if (!unpaid.equals(other.unpaid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getRemittance() + ": Payment of " + getPaymentValue() + " for S/I No. " + invoice.getId() + "'s "
                + getUnpaidValue() + " balance";
    }
}
