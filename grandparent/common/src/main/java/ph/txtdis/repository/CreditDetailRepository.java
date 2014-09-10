package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.CreditDetail;
import ph.txtdis.model.Customer;

public interface CreditDetailRepository extends CrudRepository<CreditDetail, Integer> {

    List<CreditDetail> findByCustomerAndStartDateBeforeOrderByStartDateDesc(Customer customer, LocalDate date,
            Pageable pageable);
}
