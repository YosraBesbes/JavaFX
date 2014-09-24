package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Remitted {
    Integer getId();

    String getPartnerName();

    LocalDate getOrderDate();

    BigDecimal getAmount();
}
