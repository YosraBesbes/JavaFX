package ph.txtdis.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import ph.txtdis.dto.OrderDTO;
import ph.txtdis.model.Ordered;
import ph.txtdis.model.Priced;

public interface OrderApp<D extends Priced> {
    
    void createDetailTable();
    
    void setDetail(Priced priced);
    
    void setPrice();
    
    void createDetailTableItems(OrderDTO<Ordered<Priced>, Priced> dto);
    
    void iterateDetailTableItems(List<Priced> details);
    
    LocalDate getPickerDate();
    
    void setCustomerDTO();
    
    void setItemDTO();
    
    BigDecimal getQtyPerUomUnit();
}
