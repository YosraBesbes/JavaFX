package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Receiving;
import ph.txtdis.model.ReceivingDetail;

public interface ReceivingRepository extends CrudRepository<Receiving, Integer> {

    @Query("select min(r.id) from Receiving r")
    int getMinId();

    @Query("select max(r.id) from Receiving r")
    int getMaxId();

    @Query("select r.details from Receiving r where r.id = ?1")
    List<ReceivingDetail> getDetails(int id);

    @Query("select r.orderDate from Receiving r "
            + "where r.reference = ph.txtdis.type.ReceivingReferenceType.PO and r.referenceId = ?1")
    LocalDate getDateFromPurchaseOrder(int id);
}
