package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Customer;
import ph.txtdis.model.CustomerDiscount;

public interface CustomerDiscountRepository extends CrudRepository<CustomerDiscount, Integer> {

    List<CustomerDiscount> findByCustomerAndStartDateBeforeOrderByStartDateDesc(Customer customer, LocalDate date,
            Pageable pageable);
}
