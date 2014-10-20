package ph.txtdis.repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.RemittanceSettlement;
import ph.txtdis.model.RemittanceSettlementDetail;
import ph.txtdis.model.Truck;

public interface RemittanceSettlementRepository extends CrudRepository<RemittanceSettlement, Integer> {

    @Query("select new ph.txtdis.model.RemittanceSettlementDetail(i, "
            + "(select sum(rd.payment) from RemittanceDetail rd where i = rd.invoicing), "
            + "(select s from RemittanceSettlementAdjustment s where i = s.invoice)) "
            + "from Invoicing i, Picking p, PickingDetail pd "
            + "where p = pd.picking and i.booking = pd.booking and p.truck = ?1 and i.orderDate = ?2 order by i.id ")
    List<RemittanceSettlementDetail> getDetail(Truck truck, LocalDate date);

    RemittanceSettlement findByTruckAndTimeStampBetween(Truck truck, ZonedDateTime startOfDay, ZonedDateTime endOfDay);
}
