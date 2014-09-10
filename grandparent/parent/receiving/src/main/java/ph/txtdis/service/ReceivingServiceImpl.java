package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Receiving;
import ph.txtdis.model.ReceivingDetail;
import ph.txtdis.repository.ReceivingRepository;

@Service
@Transactional()
public class ReceivingServiceImpl extends AbstractService<Receiving> implements ReceivingService {

    @Autowired
    private ReceivingRepository repository;

    protected ReceivingServiceImpl() {
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
    public List<ReceivingDetail> getDetails(int id) {
        return repository.getDetails(id);
    }
}
