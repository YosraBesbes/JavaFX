package ph.txtdis.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.StockTakeStatus;

public interface StockTakeStatusRepository extends CrudRepository<StockTakeStatus, LocalDate> {

    @Query("select s.isReconciled from StockTakeStatus s "
            + "where s.idDate = (select max(ss.idDate) from StockTakeStatus ss)")
    Boolean isStockTakeReconciled();
}
