package ph.txtdis.model;

import java.math.BigDecimal;

public interface Priced extends ItemDetailed {

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    BigDecimal getSubtotal();
}
