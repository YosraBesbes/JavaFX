package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Route;

public interface PickingRepository extends CrudRepository<Picking, Integer> {

    @Query("select min(p.id) from Picking p")
    int getMinId();

    @Query("select max(p.id) from Picking p")
    int getMaxId();
    
    @Query("select p.details from Picking p where p.id = ?1")
    List<PickingDetail> getDetails(int id);
    
    @Query("select d.booking from Picking p join p.details d where p.pickDate = ?1")
    List<Booking> getPickedBookings(LocalDate date);
    
    @Query("select distinct b.route from Booking b where b.orderDate = ?1 "
            + "and b.id not in (select d.booking from Picking p join p.details d where p.pickDate = ?1)")
    List<Route> getUnpickedRoutes(LocalDate date);
}
