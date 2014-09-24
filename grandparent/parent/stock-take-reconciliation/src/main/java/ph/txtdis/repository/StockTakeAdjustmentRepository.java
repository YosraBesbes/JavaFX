package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.StockTakeAdjustment;

public interface StockTakeAdjustmentRepository extends CrudRepository<StockTakeAdjustment, Integer> {
}
