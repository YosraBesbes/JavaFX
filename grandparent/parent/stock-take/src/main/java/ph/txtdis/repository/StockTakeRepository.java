package ph.txtdis.repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.util.TransactionStamp;

public interface StockTakeRepository extends CrudRepository<StockTake, Integer> {

    @Query("select min(s.id) from StockTake s")
    int getMinId();

    @Query("select max(s.id) from StockTake s")
    int getMaxId();

    @Query("select s.details from StockTake s where s.id = ?1")
    List<StockTakeDetail> getDetails(int id);

    @Query("select max(s.stockTakeDate) from StockTake s")
    LocalDate getLatestDate();

    @Query("select new ph.txtdis.util.TransactionStamp(s.id, s.cutoffBy, s.cutoffOn) from StockTakeReconciliation s "
            + "where s.id = (select min(st.id) from StockTakeReconciliation st where st.id > ?1)")
    TransactionStamp getCutoffStampOfStockTakeAfter(LocalDate date);

    @Query("select min(p.id) from Picking p where p.pickDate > ?1")
    Integer getOnePickingIdAfter(LocalDate date);

    @Query("select min(r.id) from Receiving r where r.orderDate > ?1")
    Integer getOneReceivingIdAfter(LocalDate date);

    @Query("select max(s.id) from StockTakeReconciliation s where s.closedBy = null and s.id < ?1")
    LocalDate getOpenStockTakeBefore(LocalDate date);

    @Query("select new ph.txtdis.util.TransactionStamp(s.id, s.closedBy, s.closedOn) from StockTakeReconciliation s "
            + "where s.id = ?1")
    TransactionStamp getClosureStamp(LocalDate date);

    @Query("select max(s.cutoffOn) from StockTakeReconciliation s")
    ZonedDateTime getLatestCutoffDate();
}
