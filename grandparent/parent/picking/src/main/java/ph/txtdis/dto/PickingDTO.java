package ph.txtdis.dto;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Route;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;

public interface PickingDTO extends SpunDTO, DTO<Picking> {

    Truck getTruck();
    
    void setTruck(Truck truck);
    
    SystemUser getDriver();
    
    void setDriver(SystemUser driver);
    
    SystemUser getHelper1();
    
    void setHelper1(SystemUser helper1);
    
    SystemUser getHelper2();
    
    void setHelper2(SystemUser helper2);
    
    LocalDate getPickDate();
    
    void setPickDate(LocalDate pickDate);
    
    String getRemarks();
    
    void setRemarks(String remarks);
    
    List<PickingDetail> getDetails();
    
    void setDetails(List<PickingDetail> details);
    
    List<Route> getRoutes(LocalDate date);
    
    List<Route> getUnpickedRoutes(LocalDate date);

    List<Booking> getBookings(Route route, LocalDate date);

    List<Booking> getUnpickedBookings(Route route, LocalDate date);
}
