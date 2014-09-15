package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
import ph.txtdis.repository.PurchasingRepository;

@Service
@Transactional()
public class PurchasingServiceImpl extends AbstractStockTakeDependentOrderService<Purchasing, PurchasingDetail>
        implements PurchasingService {

    @Autowired
    private PurchasingRepository repository;

    protected PurchasingServiceImpl() {
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
    public List<PurchasingDetail> getDetails(int id) {
        return repository.getDetails(id);
    }
}
