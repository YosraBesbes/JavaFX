package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.model.StockTakeSummary;

public interface StockTakeRepository extends CrudRepository<StockTake, Integer> {

    @Query("select min(s.id) from StockTake s")
    int getMinId();

    @Query("select max(s.id) from StockTake s")
    int getMaxId();

    @Query("select s.details from StockTake s where s.id = ?1")
    List<StockTakeDetail> getDetails(int id);

    // @Query("select new ph.txtdis.model.StockTakeSummary(s.stockTakeDate, i, d.quality, sum(d.qty * qpu.qty)) "
    // + "from StockTake s join s.details d join d.item i join i.qtyPerUom qpu "
    // +
    // "where qpu.uom = d.uom and s.stockTakeDate = ?1 group by s.stockTakeDate, i, d.quality ")
    // List<StockTakeSummary> getSummary(LocalDate date);
    List<StockTakeSummary> findByStockTakeDate(LocalDate date);
}
