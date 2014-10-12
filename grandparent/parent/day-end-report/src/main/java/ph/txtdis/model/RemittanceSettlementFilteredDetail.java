package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class RemittanceSettlementFilteredDetail {

    private ReadOnlyObjectWrapper<Invoicing> invoice;

    private ReadOnlyIntegerWrapper invoiceId;

    private ReadOnlyObjectWrapper<LocalDate> date;

    private ReadOnlyObjectWrapper<Customer> partner;

    private ReadOnlyObjectWrapper<BigDecimal> invoicedValue, remittedValue;

    private ObjectBinding<BigDecimal> varianceValue;

    private SimpleObjectProperty<BigDecimal> adjustmentValue;

    private SimpleStringProperty actionTaken;

    public RemittanceSettlementFilteredDetail(Invoicing invoice, BigDecimal remitted, BigDecimal adjustment,
            String actionTaken) {
        this.invoice = new ReadOnlyObjectWrapper<>(invoice);
        this.invoiceId = new ReadOnlyIntegerWrapper(invoice.getId());
        this.date = new ReadOnlyObjectWrapper<>(invoice.getOrderDate());
        this.partner = new ReadOnlyObjectWrapper<>(invoice.getPartner());
        this.actionTaken = new SimpleStringProperty(actionTaken);
        invoicedValue = new ReadOnlyObjectWrapper<>(invoice.getTotalValue());
        remittedValue = new ReadOnlyObjectWrapper<>(remitted);
        adjustmentValue = new SimpleObjectProperty<>(adjustment);
        varianceValue = new ObjectBinding<BigDecimal>() {
            {
                bind(invoicedValue, remittedValue, adjustmentValue);
            }

            @Override
            protected BigDecimal computeValue() {
                return remittedValue.get().subtract(invoicedValue.get()).add(adjustmentValue.get());
            }
        };
    }

    public Invoicing getInvoice() {
        return invoice.get();
    }

    public int getInvoiceId() {
        return invoiceId.get();
    }

    public LocalDate getDate() {
        return date.get();
    }

    public Customer getPartner() {
        return partner.get();
    }

    public BigDecimal getInvoicedValue() {
        return invoicedValue.get();
    }

    public BigDecimal getRemittedValue() {
        return remittedValue.get();
    }

    public BigDecimal getVarianceValue() {
        return varianceValue.get();
    }

    public BigDecimal getAdjustmentValue() {
        return adjustmentValue.get();
    }

    public void setAdjustmentValue(BigDecimal adjustmentValue) {
        this.adjustmentValue.set(adjustmentValue);
    }

    public String getActionTaken() {
        return actionTaken.get();
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken.set(actionTaken);
    }
}
