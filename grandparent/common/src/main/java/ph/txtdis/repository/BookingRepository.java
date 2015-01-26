package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.BookingDiscount;
import ph.txtdis.model.Route;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

    @Query("select min(b.id) from Booking b")
    Integer getMinId();

    @Query("select max(b.id) from Booking b")
    Integer getMaxId();

    @Query("select b.details from Booking b where b.id = ?1")
    List<BookingDetail> getDetails(int id);

    @Query("select b.discounts from Booking b where b.id = ?1")
    List<BookingDiscount> getDiscounts(int id);

    @Query("select distinct b.route from Booking b where b.orderDate = ?1")
    List<Route> getRoutes(LocalDate date);

    List<Booking> findByRouteAndOrderDate(Route route, LocalDate date);
}
