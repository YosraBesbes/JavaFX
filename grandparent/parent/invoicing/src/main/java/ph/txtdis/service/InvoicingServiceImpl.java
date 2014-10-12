package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Booking;
import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.InvoicingDetail;
import ph.txtdis.repository.InvoiceBookletRepository;
import ph.txtdis.repository.InvoicingRepository;

@Service
@Transactional()
public class InvoicingServiceImpl extends AbstractService<Invoicing, Integer> implements InvoicingService {

    @Autowired
    private InvoicingRepository repository;

    @Autowired
    private InvoiceBookletRepository bookletRepository;

    protected InvoicingServiceImpl() {
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
    public List<InvoicingDetail> getDetails(int id) {
        return repository.getDetails(id);
    }

    @Override
    public InvoiceBooklet getBooklet(int id) {
        return bookletRepository.findByStartIdLessThanEqualAndEndIdGreaterThanEqual(id, id);
    }

    @Override
    public Integer getBookletLastId(int startId, int endId) {
        return repository.getBookletLastId(startId, endId);
    }

    @Override
    public Integer getIdFromSalesOrder(Booking booking) {
        return repository.getIdFromSalesOrder(booking);
    }
}
