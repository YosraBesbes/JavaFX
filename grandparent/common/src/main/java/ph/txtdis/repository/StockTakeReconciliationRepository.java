package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;
import ph.txtdis.model.SystemUser;

public interface StockTakeReconciliationRepository extends CrudRepository<StockTakeReconciliation, LocalDate> {

    @Query("select min(s.idDate) from StockTakeReconciliation s")
    LocalDate getOldestDate();

    @Query("select max(s.idDate) from StockTakeReconciliation s")
    LocalDate getLatestDate();

    @Query("select max(s.idDate) from StockTakeReconciliation s where s.idDate < ?1 and s.isApproved = true")
    LocalDate getImmediatelyPrecedingDate(LocalDate date);

    @Query("select new ph.txtdis.model.StockTakeReconciliationDetail(i, q, "
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

    @Query("select s.approvedBy from StockTakeReconciliation s where s.idDate = "
            + "(select max(st.idDate) from StockTakeReconciliation st)")
    SystemUser getLatestApprover();
}
