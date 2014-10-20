package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.Route;
import ph.txtdis.repository.BookingRepository;

@Service
@Transactional()
public class BookingServiceImpl extends AbstractService<Booking, Integer> implements BookingService {

    @Autowired
    private BookingRepository repository;

    protected BookingServiceImpl() {
    }

    @Override
    public Integer getMinId() {
        return repository.getMinId();
    }

    @Override
    public Integer getMaxId() {
        return repository.getMaxId();
    }

    @Override
    public List<BookingDetail> getDetails(int id) {
        return repository.getDetails(id);
    }

    @Override
    public List<Route> getRoutes(LocalDate date) {
        return repository.getRoutes(date);
    }

    @Override
    public List<Booking> getBookings(Route route, LocalDate date) {
        return repository.findByRouteAndOrderDate(route, date);
    }
}
