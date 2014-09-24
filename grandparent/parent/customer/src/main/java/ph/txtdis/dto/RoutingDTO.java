package ph.txtdis.dto;

import java.time.LocalDate;

import ph.txtdis.model.Customer;
import ph.txtdis.model.Route;
import ph.txtdis.model.Routing;

public interface RoutingDTO extends Audited<Routing> {

    Customer getCustomer();

    void setCustomer(Customer customer);
    
    Route getRoute();
    
    void setRoute(Route route);
    
    LocalDate getStartDate();
    
    void setStartDate(LocalDate startDate);
}