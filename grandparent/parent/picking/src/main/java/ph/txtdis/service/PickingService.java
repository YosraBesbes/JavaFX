package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Route;

public interface PickingService extends SpunServiced<Picking> {
    
    List<PickingDetail> getDetails(int id);
    
    List<Booking> getPickedBookings(LocalDate date);
    
    List<Route> getUnpickedRoutes(LocalDate date);
}