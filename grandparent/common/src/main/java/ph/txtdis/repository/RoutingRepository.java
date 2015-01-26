package ph.txtdis.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Customer;
import ph.txtdis.model.Routing;

public interface RoutingRepository extends CrudRepository<Routing, Integer> {

    Routing findFirstByCustomerAndStartDateLessThanEqualOrderByStartDateDesc(Customer customer, LocalDate date);
}
