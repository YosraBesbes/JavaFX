package ph.txtdis.dto;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Route;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;
import ph.txtdis.service.BookingService;
import ph.txtdis.service.PickingService;

@Component
public class PickingDTOImpl extends AbstractSpunDTO<Picking, PickingService> implements PickingDTO {

    @Autowired
    private BookingService bookingService;

    @Override
    public void reset() {
        id = 0;
        entity = new Picking();
    }

    @Override
    public Truck getTruck() {
        return entity.getTruck();
    }

    @Override
    public void setTruck(Truck truck) {
        entity.setTruck(truck);
    }

    @Override
    public SystemUser getDriver() {
        return entity.getDriver();
    }

    @Override
    public void setDriver(SystemUser driver) {
        entity.setDriver(driver);
    }

    @Override
    public SystemUser getHelper1() {
        return entity.getHelper1();
    }

    @Override
    public void setHelper1(SystemUser helper1) {
        entity.setHelper1(helper1);
    }

    @Override
    public SystemUser getHelper2() {
        return entity.getHelper2();
    }

    @Override
    public void setHelper2(SystemUser helper2) {
        entity.setHelper2(helper2);
    }

    @Override
    public LocalDate getPickDate() {
        return entity.getPickDate();
    }

    @Override
    public void setPickDate(LocalDate pickDate) {
        entity.setPickDate(pickDate);
    }

    @Override
    public String getRemarks() {
        return entity.getRemarks();
    }

    @Override
    public void setRemarks(String remarks) {
        entity.setRemarks(remarks);
    }

    @Override
    public ObservableList<PickingDetail> getDetails() {
        return FXCollections.observableList(service.getDetails(id));
    }

    @Override
    public void setDetails(List<PickingDetail> details) {
        entity.setDetails(details);
    }

    @Override
    public List<Route> getRoutes(LocalDate date) {
        return bookingService.getRoutes(date);
    }

    @Override
    public List<Route> getUnpickedRoutes(LocalDate date) {
        return service.getUnpickedRoutes(date);
    }

    @Override
    public List<Booking> getBookings(Route route, LocalDate date) {
        return bookingService.getBookings(route, date);
    }

    @Override
    public List<Booking> getUnpickedBookings(Route route, LocalDate date) {
        List<Booking> bookings = getBookings(route, date);
        bookings.removeAll(service.getPickedBookings(date));
        return service.getPickedBookings(date);
    }
}
