package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.ItemSummary;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;

public interface StockTakeReconciliationRepository extends CrudRepository<StockTakeReconciliation, LocalDate> {

    @Query("select min(s.idDate) from StockTakeReconciliation s")
    LocalDate getOldestDate();

    @Query("select max(s.idDate) from StockTakeReconciliation s")
    LocalDate getLatestDate();

    @Query("select s.details from StockTakeReconciliation s where s.idDate = ?1")
    List<StockTakeReconciliationDetail> getDetails(LocalDate idDate);

    @Query("select new ph.txtdis.model.ItemSummary(i, q, "
            + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate = ?1 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select sum(s.qty) from ReceivingSummary s where s.orderDate between ?1 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select sum(s.qty) from PickingSummary s where s.pickDate between ?1 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate = ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality)) from Item i, Quality q "
            + "where (3 is null) order by i.id, q.id")
    List<ItemSummary> getItemSummary(LocalDate startDate, LocalDate endDate);
}
