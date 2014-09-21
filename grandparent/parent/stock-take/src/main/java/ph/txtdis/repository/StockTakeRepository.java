package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;

public interface StockTakeRepository extends CrudRepository<StockTake, Integer> {

    @Query("select min(s.id) from StockTake s")
    int getMinId();

    @Query("select max(s.id) from StockTake s")
    int getMaxId();

    @Query("select s.details from StockTake s where s.id = ?1")
    List<StockTakeDetail> getDetails(int id);

    @Query("select max(s.stockTakeDate) from StockTake s")
    LocalDate getLatestDate();
}
