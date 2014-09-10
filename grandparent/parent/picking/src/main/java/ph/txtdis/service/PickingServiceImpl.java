package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Route;
import ph.txtdis.repository.PickingRepository;

@Service
@Transactional()
public class PickingServiceImpl extends AbstractService<Picking> implements PickingService {

    @Autowired
    private PickingRepository repository;

    protected PickingServiceImpl() {
    }

    @Override
    public int getMinId() {
        return repository.getMinId();
    }

    @Override
    public int getMaxId() {
        return repository.getMaxId();
    }

    @Override
    public List<PickingDetail> getDetails(int id) {
        return repository.getDetails(id);
    }

    @Override
    public List<Booking> getPickedBookings(LocalDate date) {
        return repository.getPickedBookings(date);
    }

    @Override
    public List<Route> getUnpickedRoutes(LocalDate date) {
        return repository.getUnpickedRoutes(date);
    }
}
