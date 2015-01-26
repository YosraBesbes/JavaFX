package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RemittanceSettlementDetail {

    private Invoicing invoice;

    private BigDecimal payment;

    private String actionTaken;

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
        return invoice == null ? BigDecimal.ZERO : invoice.getValue();
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
}
