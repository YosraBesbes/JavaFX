package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.StockTakeSummary;

public interface StockTakeSummaryRepository extends CrudRepository<StockTakeSummary, Integer> {

    List<StockTakeSummary> findByStockTakeDate(LocalDate date);
}
