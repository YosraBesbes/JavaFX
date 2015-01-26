package ph.txtdis.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class Vat {

    private Invoicing invoice;

    private LocalDate date;

    public Vat(Invoicing invoice) {
        this.invoice = invoice;
    }

    public int getId() {
        return invoice == null ? 0 : invoice.getId();
    }

    public LocalDate getDate() {
        return invoice == null ? date : invoice.getOrderDate();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getPartner() {
        return invoice == null ? null : invoice.getPartner();
    }

    public BigDecimal getTotalValue() {
        return invoice == null ? BigDecimal.ZERO : invoice.getValue();
    }

    public BigDecimal getVatValue() {
        return getTotalValue().multiply(new BigDecimal(0.12, new MathContext(2, RoundingMode.HALF_EVEN)));
    }
}