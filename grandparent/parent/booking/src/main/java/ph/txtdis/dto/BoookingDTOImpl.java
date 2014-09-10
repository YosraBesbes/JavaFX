package ph.txtdis.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.service.BookingService;

@Component
public class BoookingDTOImpl extends AbstractOrderDTO<Booking, BookingService, BookingDetail> implements
        BookingDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Booking();
    }

    @Override
    public ObservableList<BookingDetail> getDetails() {
        return FXCollections.observableList(service.getDetails(id));
    }
}
