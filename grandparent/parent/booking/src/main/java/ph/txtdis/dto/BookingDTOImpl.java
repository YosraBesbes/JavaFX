package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.BookingDiscount;
import ph.txtdis.model.Users;
import ph.txtdis.service.BookingService;
import ph.txtdis.service.CustomerService;

@Component
public class BookingDTOImpl extends AbstractOrderDTO<Booking, BookingService, BookingDetail> implements BookingDTO {

    @Autowired
    CustomerService customerService;

    private List<BookingDetail> newDetails = new ArrayList<>();

    @Override
    public void reset() {
        id = 0;
        entity = new Booking();
        newDetails = new ArrayList<>();
    }

    @Override
    public ObservableList<BookingDetail> getDetails() {
        return FXCollections.observableList(newDetails.isEmpty() ? service.getDetails(id) : newDetails);
    }

    @Override
    public Users getPrintedBy() {
        return entity.getPrintedBy();
    }

    @Override
    public void setPrintedBy(Users printedBy) {
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
    public void reorder() {
        setForReorder(getPartnerId(), getDetails());
    }

    private void setForReorder(int partnerId, List<BookingDetail> oldDetails) {
        reset();
        setOrderDate(LocalDate.now());
        setPartner(customerService.get(partnerId));
        setNewDetails(oldDetails);
    }

    private void setNewDetails(List<BookingDetail> oldDetails) {
        oldDetails.forEach(oldDetail -> {
            BookingDetail newDetail = new BookingDetail(get(), oldDetail.getItem(), oldDetail.getUom(), oldDetail
                    .getQty(), oldDetail.getQuality());
            newDetail.setPrice(oldDetail.getPrice());
            newDetails.add(newDetail);
        });
    }

    @Override
    public List<BookingDiscount> getDiscounts() {
        return service.getDiscounts(id);
    }

    @Override
    public void setDiscounts(List<BookingDiscount> discounts) {
        entity.setDiscounts(discounts);
    }
}
