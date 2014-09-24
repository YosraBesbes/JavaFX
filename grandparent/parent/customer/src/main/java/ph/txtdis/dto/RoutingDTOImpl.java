package ph.txtdis.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Customer;
import ph.txtdis.model.Route;
import ph.txtdis.model.Routing;
import ph.txtdis.service.RoutingService;

@Component
public class RoutingDTOImpl extends AbstractAuditedDTO<Routing, RoutingService> implements RoutingDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Routing();
    }

    @Override
    public Customer getCustomer() {
        return entity.getCustomer();
    }

    @Override
    public void setCustomer(Customer customer) {
        entity.setCustomer(customer);
    }

    @Override
    public Route getRoute() {
        return entity.getRoute();
    }

    @Override
    public void setRoute(Route route) {
        entity.setRoute(route);
    }

    @Override
    public LocalDate getStartDate() {
        return entity.getStartDate();
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        entity.setStartDate(startDate);
    }
}
