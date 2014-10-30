package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RemittanceSettlementDetail {

    private Invoicing invoice;

    private BigDecimal payment;

    private String actionTaken;

    public RemittanceSettlementDetail(Invoicing invoice, BigDecimal payment, String actionTaken) {

        this.invoice = invoice;
        this.payment = payment;
        this.actionTaken = actionTaken;
    }

    public Invoicing getInvoice() {
        return invoice;
    }

    public int getInvoiceId() {
        return invoice == null ? 0 : invoice.getId();
    }

    public LocalDate getDate() {
        return invoice == null ? null : invoice.getOrderDate();
    }

    public Customer getPartner() {
        return invoice == null ? null : invoice.getPartner();
    }

    public BigDecimal getInvoicedValue() {
        return invoice == null ? BigDecimal.ZERO : invoice.getTotalValue();
    }

    public BigDecimal getVarianceValue() {
        return getInvoicedValue().subtract(getRemittedValue());
    }

    public BigDecimal getRemittedValue() {
        return payment == null ? BigDecimal.ZERO : payment;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actionTaken == null) ? 0 : actionTaken.hashCode());
        result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
        result = prime * result + ((payment == null) ? 0 : payment.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RemittanceSettlementDetail other = (RemittanceSettlementDetail) obj;
        if (actionTaken == null) {
            if (other.actionTaken != null)
                return false;
        } else if (!actionTaken.equals(other.actionTaken))
            return false;
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
        return true;
    }

    @Override
    public String toString() {
        return "RemittanceSettlementDetail [invoice=" + invoice + ", payment=" + payment + ", actionTaken="
                + actionTaken + "]";
    }
}
