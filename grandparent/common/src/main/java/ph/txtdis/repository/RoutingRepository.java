package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Customer;
import ph.txtdis.model.Routing;

public interface RoutingRepository extends CrudRepository<Routing, Integer> {

    List<Routing> findByCustomerAndStartDateBeforeOrderByStartDateDesc(Customer customer, LocalDate date,
            Pageable pageable);
}
