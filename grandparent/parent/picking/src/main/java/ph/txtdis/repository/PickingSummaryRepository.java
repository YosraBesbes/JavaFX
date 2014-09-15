package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.PickingSummary;

public interface PickingSummaryRepository extends CrudRepository<PickingSummary, Integer> {

    List<PickingSummary> findByPickDateBetween(LocalDate startDate, LocalDate endDate);
}
