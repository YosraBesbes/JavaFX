package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javafx.collections.ObservableList;
import ph.txtdis.model.Booking;
import ph.txtdis.model.PickList;
import ph.txtdis.model.PickListPrinting;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Route;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;

public interface PickingDTO extends Spun, Audited<Picking> {

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

    SystemUser getPrintedBy();

    void setPrintedBy(SystemUser printedBy);

    ZonedDateTime getPrintedOn();

    void setPrintedOn(ZonedDateTime printedOn);

    List<PickList> getPickList();

    List<Route> getRoutes(LocalDate date);

    List<Route> getNotFullyPickedRoutes(LocalDate date);

    List<Booking> getUnpickedBookings(LocalDate date, Route route);

    ObservableList<Truck> getEmptyTrucks(LocalDate date);

    PickListPrinting getPrintedPickList(Picking picking);
}
