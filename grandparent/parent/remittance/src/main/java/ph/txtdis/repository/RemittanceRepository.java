package ph.txtdis.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.RemittanceDetail;
import ph.txtdis.model.Truck;

public interface RemittanceRepository extends CrudRepository<Remittance, Integer> {

    @Query("select min(r.id) from Remittance r")
    int getMinId();

    @Query("select max(r.id) from Remittance r")
    int getMaxId();

    @Query("select r.details from Remittance r where r.id = ?1")
    List<RemittanceDetail> getDetails(int id);

    @Query("select r from Remittance r join r.details rd, Invoicing i, Picking p join p.details pd "
            + "where rd.invoicing = i and i.booking = pd.booking and r.orderDate = ?1 and p.truck = ?2 ")
    List<Remittance> getRemittances(LocalDate date, Truck truck);

    @Query("select sum(d.payment) from Remittance r join r.details d where d.invoicing = ?1")
    BigDecimal getPayment(Invoicing invoice);

    List<Remittance> findByCreatedDateBetweenOrderByIdAsc(ZonedDateTime zdtStart, ZonedDateTime zdtEnd);
}
