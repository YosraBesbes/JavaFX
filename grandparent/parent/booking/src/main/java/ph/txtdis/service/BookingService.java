package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.Route;

public interface BookingService extends OrderService<Booking, BookingDetail> {

    List<Route> getRoutes(LocalDate date);

    List<Booking> getBookings(Route route, LocalDate date);
}