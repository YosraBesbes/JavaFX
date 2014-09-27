package ph.txtdis.dto;

import java.time.ZonedDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.BookingService;

@Component
public class BookingDTOImpl extends AbstractOrderDTO<Booking, BookingService, BookingDetail> implements BookingDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Booking();
    }

    @Override
    public ObservableList<BookingDetail> getDetails() {
        return FXCollections.observableList(service.getDetails(id));
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
}
