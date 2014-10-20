package ph.txtdis.repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.LoadSettlement;
import ph.txtdis.model.LoadSettlementDetail;
import ph.txtdis.model.Truck;

public interface LoadSettlementRepository extends CrudRepository<LoadSettlement, Integer> {

    @Query("select new ph.txtdis.model.LoadSettlementDetail(i, "
            + "(select sum(case when s.qty is null then 0.00 else s.qty end) from PickingSummaryByTruck s "
            + "  where s.truck = ?1 and s.pickDate = ?2 and i = s.item group by s.item), "
            + "(select sum(case when s.qty is null then 0.00 else s.qty end) from InvoicedVolumeByTruckView s "
            + "  where s.truck = ?1 and s.orderDate = ?2 and i = s.item group by s.item), "
            + "(select sum(case when s.qty is null then 0.00 else s.qty end) from ReceivingSummaryByTruck s "
            + "  where s.truck = ?1 and s.orderDate = ?2 and i = s.item group by s.item), "
            + "(select s.qty from LoadSettlementAdjustment s where s.truck = ?1 and s.pickDate = ?2 and i = s.item), "
            + "(select s.actionTaken from LoadSettlementAdjustment s where s.truck = ?1 and s.pickDate = ?2 "
            + "    and i = s.item)) from Item i order by i.id ")
    List<LoadSettlementDetail> getDetail(Truck truck, LocalDate date);

    LoadSettlement findByTruckAndTimeStampBetween(Truck truck, ZonedDateTime startOfDay, ZonedDateTime endOfDay);
}
