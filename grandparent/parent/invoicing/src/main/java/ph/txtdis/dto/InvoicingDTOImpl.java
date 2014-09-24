package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.InvoicingDetail;
import ph.txtdis.service.InvoicingService;

@Component
public class InvoicingDTOImpl extends AbstractOrderDTO<Invoicing, InvoicingService, InvoicingDetail> implements
        InvoicingDTO {
    
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
    public Integer getIdBySalesOrder(Booking booking) {
        return service.getIdBySalesOrder(booking);
    }
}
