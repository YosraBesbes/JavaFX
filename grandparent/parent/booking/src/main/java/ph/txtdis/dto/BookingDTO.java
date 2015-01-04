package ph.txtdis.dto;

import java.util.List;

import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.BookingDiscount;

public interface BookingDTO extends OrderDTO<Booking, BookingDetail>, Printed, Reorder {

    List<BookingDiscount> getDiscounts();

    void setDiscounts(List<BookingDiscount> discounts);
}
