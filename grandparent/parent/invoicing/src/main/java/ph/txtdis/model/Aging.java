package ph.txtdis.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Aging {

    private Customer customer;

    private BigDecimal totalValue = BigDecimal.ZERO;

    private BigDecimal currentValue = BigDecimal.ZERO;

    private BigDecimal oneToSevenValue = BigDecimal.ZERO;

    private BigDecimal eightToFifteenValue = BigDecimal.ZERO;

    private BigDecimal sixteenToThirtyValue = BigDecimal.ZERO;

    private BigDecimal greaterThanThirtyValue = BigDecimal.ZERO;

    public int getCustomerId() {
        return customer == null ? 0 : customer.getId();
    }
}