package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.Booking;
import ph.txtdis.model.PickList;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.PickingSummary;
import ph.txtdis.model.Route;
import ph.txtdis.model.Truck;

public interface PickingService extends SpunService<Picking, Integer> {

    List<PickingDetail> getDetails(int id);

    List<Booking> getUnpickedBookings(LocalDate date, Route route);

    List<Route> getNotFullyPickedRoutes(LocalDate date);

    List<Truck> getEmptyTrucks(LocalDate date);

    List<Truck> getLoadedTrucks(LocalDate date);

    List<PickList> generatePickList(int id);

    List<PickingSummary> getSummary(LocalDate startDate, LocalDate endDate);

    LocalDate getPickDateFromSalesOrder(Booking booking);
}