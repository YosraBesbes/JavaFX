package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Booking;
import ph.txtdis.model.PickList;
import ph.txtdis.model.PickListPrinting;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.PickingSummary;
import ph.txtdis.model.Route;
import ph.txtdis.model.Truck;
import ph.txtdis.repository.PickListPrintingRepository;
import ph.txtdis.repository.PickingRepository;
import ph.txtdis.repository.PickingSummaryRepository;

@Service
@Transactional()
public class PickingServiceImpl extends AbstractIdService<Picking> implements PickingService {

    @Autowired
    private PickingRepository repository;

    @Autowired
    private PickingSummaryRepository summerRepository;

    @Autowired
    private PickListPrintingRepository printingRepository;

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
    public List<Booking> getUnpickedBookings(LocalDate date, Route route) {
        return repository.getUnpickedBookings(date, route);
    }

    @Override
    public List<Route> getNotFullyPickedRoutes(LocalDate date) {
        return repository.getNotFullyPickedRoutes(date);
    }

    @Override
    public List<Truck> getEmptyTrucks(LocalDate date) {
        return repository.getEmptyTrucks(date);
    }

    @Override
    public List<PickList> generatePickList(int id) {
        return repository.generatePickList(id);
    }

    @Override
    public PickListPrinting getPrintedPickList(Picking picking) {
        return printingRepository.findByPicking(picking);
    }

    @Override
    public List<PickingSummary> getSummary(LocalDate startDate, LocalDate endDate) {
        return summerRepository.findByPickDateBetween(startDate, endDate);
    }
}
