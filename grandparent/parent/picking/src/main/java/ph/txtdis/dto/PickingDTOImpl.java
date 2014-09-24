package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.PickList;
import ph.txtdis.model.PickListPrinting;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Route;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;
import ph.txtdis.service.BookingService;
import ph.txtdis.service.PickingService;

@Component
public class PickingDTOImpl extends AbstractSpunById<Picking, PickingService> implements PickingDTO {

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
    public SystemUser getPrintedBy() {
        return entity.getPrintedBy();
    }

    @Override
    public void setPrintedBy(SystemUser printedBy) {
        entity.setPrintedBy(printedBy);
    }

    @Override
    public ZonedDateTime getPrintedOn() {
        return entity.getPrintedOn();
    }

    @Override
    public void setPrintedOn(ZonedDateTime printedOn) {
        entity.setPrintedOn(printedOn);
    }

    @Override
    public List<PickList> getPickList() {
        return service.generatePickList(id);
    }

    @Override
    public List<Route> getRoutes(LocalDate date) {
        return bookingService.getRoutes(date);
    }

    @Override
    public List<Route> getNotFullyPickedRoutes(LocalDate date) {
        return service.getNotFullyPickedRoutes(date);
    }

    @Override
    public List<Booking> getUnpickedBookings(LocalDate date, Route route) {
        return service.getUnpickedBookings(date, route);
    }

    @Override
    public ObservableList<Truck> getEmptyTrucks(LocalDate date) {
        ObservableList<Truck> trucks = FXCollections.observableList(service.getEmptyTrucks(date));
        return trucks.isEmpty() ? FXCollections.observableList(Arrays.asList(getTruck())) : trucks;
    }

    @Override
    public PickListPrinting getPrintedPickList(Picking picking) {
        return service.getPrintedPickList(picking);
    }
}
