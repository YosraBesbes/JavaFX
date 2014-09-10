package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.model.StockTake;

public interface StockTakeRepository extends CrudRepository<StockTake, Integer> {

    @Query("select min(s.id) from StockTake s")
    int getMinId();

    @Query("select max(s.id) from StockTake s")
    int getMaxId();
    
    @Query("select s.details from StockTake s where s.id = ?1")
    List<StockTakeDetail> getDetails(int id);
}
