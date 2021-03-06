package ph.txtdis.dto;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.InvoicingDetail;
import ph.txtdis.service.InvoicingService;
import ph.txtdis.service.PickingService;

@Component
public class InvoicingDTOImpl extends AbstractOrderDTO<Invoicing, InvoicingService, InvoicingDetail> implements
        InvoicingDTO {

    @Autowired
    PickingService pickingService;

    @Override
    public void reset() {
        id = 0;
        entity = new Invoicing();
    }

    @Override
    public int getBookingId() {
        return entity.getBooking() == null ? 0 : entity.getBooking().getId();
    }

    @Override
    public Booking getBooking() {
        return entity.getBooking();
    }

    @Override
    public void setBooking(Booking booking) {
        entity.setBooking(booking);
    }

    @Override
    public InvoiceBooklet getBooklet(int id) {
        return service.getBooklet(id);
    }

    @Override
    public Integer getBookletLastId(int startId, int endId) {
        return service.getBookletLastId(startId, endId);
    }

    @Override
    public Integer getIdFromSalesOrder(Booking booking) {
        return service.getIdFromSalesOrder(booking);
    }

    @Override
    public LocalDate getPickDateFromSalesOrder(Booking booking) {
        return pickingService.getPickDateFromSalesOrder(booking);
    }
}
