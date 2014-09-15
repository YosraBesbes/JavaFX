package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Remittance;
import ph.txtdis.model.RemittanceDetail;
import ph.txtdis.repository.RemittanceRepository;

@Service
@Transactional()
public class RemittanceServiceImpl extends AbstractIdService<Remittance> implements RemittanceService {

    @Autowired
    private RemittanceRepository repository;

    protected RemittanceServiceImpl() {
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
    public List<RemittanceDetail> getDetails(int id) {
        return repository.getDetails(id);
    }
}
