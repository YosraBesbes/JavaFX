package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.InvoicingDetail;
import ph.txtdis.model.Truck;

public interface InvoicingRepository extends CrudRepository<Invoicing, Integer> {

    @Query("select min(i.id) from Invoicing i")
    int getMinId();

    @Query("select max(i.id) from Invoicing i")
    int getMaxId();

    @Query("select i.details from Invoicing i where i.id = ?1")
    List<InvoicingDetail> getDetails(int id);

    @Query("select max(i.id) from Invoicing i where i.id between ?1 and ?2")
    Integer getBookletLastId(int startId, int endId);

    @Query("select i.id from Invoicing i where i.booking = ?1")
    Integer getIdFromSalesOrder(Booking booking);

    @Query("select i from Invoicing i, Picking p join p.details d "
            + "where i.booking = d.booking and i.orderDate = ?1 and p.truck = ?2 ")
    List<Invoicing> getInvoices(LocalDate date, Truck truck);

    List<Invoicing> findByOrderDateOrderByIdAsc(LocalDate date);
}
