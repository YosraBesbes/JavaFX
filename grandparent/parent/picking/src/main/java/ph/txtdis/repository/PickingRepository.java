package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Booking;
import ph.txtdis.model.PickList;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Route;
import ph.txtdis.model.Truck;

public interface PickingRepository extends CrudRepository<Picking, Integer> {

    @Query("select min(p.id) from Picking p")
    int getMinId();

    @Query("select max(p.id) from Picking p")
    int getMaxId();

    @Query("select p.details from Picking p where p.id = ?1")
    List<PickingDetail> getDetails(int id);

    @Query("select distinct b from Booking b where b.orderDate = ?1 and b.route = ?2 "
            + "and b.id not in (select d.booking from Picking p join p.details d where p.pickDate = ?1)")
    List<Booking> getUnpickedBookings(LocalDate date, Route route);

    @Query("select distinct b.route from Booking b where b.orderDate = ?1 "
            + "and b.id not in (select d.booking from Picking p join p.details d where p.pickDate = ?1)")
    List<Route> getNotFullyPickedRoutes(LocalDate date);

    @Query("select t from Truck t where t.id not in (select p.truck from Picking p where p.pickDate = ?1)")
    List<Truck> getEmptyTrucks(LocalDate date);

    @Query("select p.truck from Picking p where p.pickDate = ?1 ")
    List<Truck> getLoadedTrucks(LocalDate date);

    @Query("select new ph.txtdis.model.PickList(i.id, i.name, sum(bd.qty * qpu.qty)) "
            + "from Picking p join p.details pd join pd.booking b join b.details bd join bd.item i "
            + "join i.qtyPerUom qpu where qpu.uom = bd.uom and p.id = ?1 group by i.id, i.name")
    List<PickList> generatePickList(int id);

    @Query("select p.pickDate from Picking p join p.details d where d.booking = ?1")
    LocalDate getPickDateFromSalesOrder(Booking booking);
}
