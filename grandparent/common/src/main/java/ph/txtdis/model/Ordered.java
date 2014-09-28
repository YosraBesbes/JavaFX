package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface Ordered<D extends ItemDetailed> {

    Customer getPartner();

    void setPartner(Customer partner);

    int getPartnerId();

    String getPartnerName();

    String getPartnerAddress();

    LocalDate getOrderDate();

    void setOrderDate(LocalDate orderDate);

    String getRemarks();

    void setRemarks(String remarks);

    List<D> getDetails();

    void setDetails(List<D> details);
    
    BigDecimal getAmount();
}
