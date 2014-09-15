package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.ReceivingSummary;

public interface ReceivingSummaryRepository extends CrudRepository<ReceivingSummary, Integer> {

    List<ReceivingSummary> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
}
