package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.CreditDetail;
import ph.txtdis.model.Customer;
import ph.txtdis.model.CustomerDiscount;
import ph.txtdis.model.ItemDetailed;
import ph.txtdis.model.Ordered;
import ph.txtdis.model.Route;

public interface OrderDTO<E extends Ordered<D>, D extends ItemDetailed> extends DTO<E>, SpunDTO{
    
    void setPartner(Customer partner);

    int getPartnerId();
        
    String getPartnerName();
    
    String getPartnerAddress();
    
    LocalDate getOrderDate();
    
    void setOrderDate(LocalDate orderDate);
    
    String getRemarks();
    
    void setRemarks(String remarks);
    
    void setRoute(Route route);
    
    void setCredit(CreditDetail credit);
    
    void setDiscount(CustomerDiscount discount);
    
    BigDecimal getAmount();
    
    void setAmount(BigDecimal amount);
    
    List<D> getDetails();
    
    void setDetails(List<D> details);
}
