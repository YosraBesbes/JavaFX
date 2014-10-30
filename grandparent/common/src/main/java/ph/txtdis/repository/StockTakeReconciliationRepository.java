package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Inventory;
import ph.txtdis.model.Item;
import ph.txtdis.model.Quality;
import ph.txtdis.model.StockTakeDependent;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;
import ph.txtdis.util.TransactionStamp;

public interface StockTakeReconciliationRepository extends CrudRepository<StockTakeReconciliation, LocalDate>,
        StockTakeDependent {

    @Query("select min(s.id) from StockTakeReconciliation s")
    LocalDate getOldestDate();

    @Query("select max(s.id) from StockTakeReconciliation s")
    LocalDate getLatestDate();

    @Query("select max(s.id) from StockTakeReconciliation s where s.id < ?1 and s.isApproved = true")
    LocalDate getImmediatelyPrecedingDate(LocalDate date);

    @Query("    select new ph.txtdis.model.StockTakeReconciliationDetail(i, q, "
            + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate = ?1 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select s.qty from StockTakeAdjustment s where s.stockTakeDate = ?1 and i = s.item and q = s.quality), "
            + "(select sum(s.qty) from ReceivingSummary s where s.orderDate between ?1 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select sum(s.qty) from PickingSummary s where s.pickDate between ?1 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate = ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select s.qty from StockTakeAdjustment s where s.stockTakeDate = ?2 and i = s.item and q = s.quality), "
            + "(select s.justification from StockTakeAdjustment s where s.stockTakeDate = ?2 "
            + "    and i = s.item and q = s.quality)) from Item i, Quality q order by i.id, q.id")
    List<StockTakeReconciliationDetail> getDetail(LocalDate startDate, LocalDate endDate);

    @Override
    @Query("  select new ph.txtdis.util.TransactionStamp(s.id, s.cutoffBy, s.cutoffOn) from StockTakeReconciliation s "
            + "where s.id = (select max(st.id) from StockTakeReconciliation st where st.completedBy is null)")
    TransactionStamp getOnGoingStockTakeCutoffStamp();

    @Override
    @Query("    select new ph.txtdis.util.TransactionStamp(s.id, s.completedBy, s.completedOn) "
            + "   from StockTakeReconciliation s where s.id = "
            + "(select max(st.id) from StockTakeReconciliation st where st.completedBy is not null)")
    TransactionStamp getLatestCompletedStockTakeCompletionStamp();

    @Query("    select new ph.txtdis.model.Inventory(i, q, "
            + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate = ?1 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select s.qty from StockTakeAdjustment s where s.stockTakeDate = ?1 and i = s.item and q = s.quality), "
            + "(select sum(s.qty) from ReceivingSummary s where s.orderDate between ?1 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select sum(s.qty) from PickingSummary s where s.pickDate between ?1 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select sum(s.qty) from InvoicedVolumeView s where s.orderDate between ?3 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality)) "
            + "from Item i, Quality q order by i.id, q.id")
    List<Inventory> getInventory(LocalDate startDate, LocalDate endDate, LocalDate cutoffDate);

    @Query("    select new ph.txtdis.model.Inventory(i, q, "
            + "(select sum(s.qty) from StockTakeSummary s where s.stockTakeDate = ?1 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select s.qty from StockTakeAdjustment s where s.stockTakeDate = ?1 and i = s.item and q = s.quality), "
            + "(select sum(s.qty) from ReceivingSummary s where s.orderDate between ?1 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select sum(s.qty) from PickingSummary s where s.pickDate between ?1 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality), "
            + "(select sum(s.qty) from InvoicedVolumeView s where s.orderDate between ?3 and ?2 "
            + "    and i = s.item and q = s.quality group by s.item, s.quality)) "
            + "from Item i, Quality q where i = ?4 and q = ?5 ")
    Inventory getStockOnHand(LocalDate countDate, LocalDate currentDate, LocalDate cutoffDate, Item item,
            Quality quality);
}
