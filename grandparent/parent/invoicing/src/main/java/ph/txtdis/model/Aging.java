package ph.txtdis.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class Aging {

    private Customer customer;

    private BigDecimal totalValue, currentValue, oneToSevenValue, eightToFifteenValue, sixteenToThirtyValue,
            greaterThanThirtyValue;

    public Aging() {
    }

    public Aging(Customer customer, BigDecimal totalValue, BigDecimal currentValue, BigDecimal oneToSevenValue,
            BigDecimal eightToFifteenValue, BigDecimal sixteenToThirtyValue, BigDecimal greaterThanThirtyValue) {
        this.customer = customer;
        this.totalValue = totalValue;
        this.currentValue = currentValue;
        this.oneToSevenValue = oneToSevenValue;
        this.eightToFifteenValue = eightToFifteenValue;
        this.sixteenToThirtyValue = sixteenToThirtyValue;
        this.greaterThanThirtyValue = greaterThanThirtyValue;
    }

    public int getCustomerId() {
        return customer == null ? 0 : customer.getId();
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getTotalValue() {
        return totalValue == null ? BigDecimal.ZERO : totalValue;
    }

    public BigDecimal getCurrentValue() {
        return currentValue == null ? BigDecimal.ZERO : currentValue;
    }

    public BigDecimal getOneToSevenValue() {
        return oneToSevenValue == null ? BigDecimal.ZERO : oneToSevenValue;
    }

    public BigDecimal getEightToFifteenValue() {
        return eightToFifteenValue == null ? BigDecimal.ZERO : eightToFifteenValue;
    }

    public BigDecimal getSixteenToThirtyValue() {
        return sixteenToThirtyValue == null ? BigDecimal.ZERO : sixteenToThirtyValue;
    }

    public BigDecimal getGreaterThanThirtyValue() {
        return greaterThanThirtyValue == null ? BigDecimal.ZERO : greaterThanThirtyValue;
    }
}