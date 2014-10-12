package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Remitted {

    Integer getId();

    Customer getPartner();

    LocalDate getOrderDate();

    BigDecimal getTotalValue();

    BigDecimal getPaidValue();

    void setPaidValue(BigDecimal payment);
}
