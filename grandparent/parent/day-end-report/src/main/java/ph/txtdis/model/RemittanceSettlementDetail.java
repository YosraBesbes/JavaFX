package ph.txtdis.model;

import java.math.BigDecimal;

public class RemittanceSettlementDetail {

    private Invoicing invoice;

    private BigDecimal payment;

    private RemittanceSettlementAdjustment settlement;

    public RemittanceSettlementDetail(Invoicing invoice, BigDecimal payment, RemittanceSettlementAdjustment settlement) {

        this.invoice = invoice;
        this.payment = payment;
        this.settlement = settlement;
    }

    public Invoicing getInvoice() {
        return invoice;
    }

    public BigDecimal getVariance() {
        return invoice.getTotalValue().subtract(getRemittedValue());
    }

    public BigDecimal getRemittedValue() {
        return payment == null ? BigDecimal.ZERO : payment;
    }

    public BigDecimal getAdjustmentValue() {
        return settlement == null ? BigDecimal.ZERO : settlement.getTotalValue();
    }

    public String getActionTaken() {
        return settlement == null ? null : settlement.getActionTaken();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((payment == null) ? 0 : payment.hashCode());
        result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
        result = prime * result + ((settlement == null) ? 0 : settlement.hashCode());
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
        if (payment == null) {
            if (other.payment != null)
                return false;
        } else if (!payment.equals(other.payment))
            return false;
        if (invoice == null) {
            if (other.invoice != null)
                return false;
        } else if (!invoice.equals(other.invoice))
            return false;
        if (settlement == null) {
            if (other.settlement != null)
                return false;
        } else if (!settlement.equals(other.settlement))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RemittanceSettlementDetail [invoice=" + invoice + ", detail=" + payment + ", settlement=" + settlement
                + "]";
    }
}
