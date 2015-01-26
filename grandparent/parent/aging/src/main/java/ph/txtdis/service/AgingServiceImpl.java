package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Aging;
import ph.txtdis.repository.InvoicingRepository;

@Service
@Transactional()
public class AgingServiceImpl implements AgingService {

    @Autowired
    private InvoicingRepository repository;

    @Override
    public List<Aging> getAgingList() {
        return repository.getAgingList();
    }
}
