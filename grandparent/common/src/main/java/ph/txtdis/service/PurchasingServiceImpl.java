package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Inventory;
import ph.txtdis.model.Item;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
import ph.txtdis.model.Quality;
import ph.txtdis.repository.PurchasingRepository;
import ph.txtdis.repository.StockTakeReconciliationRepository;

@Service
@Transactional()
public class PurchasingServiceImpl extends AbstractService<Purchasing, Integer> implements PurchasingService {

    @Autowired
    private PurchasingRepository repository;

    @Autowired
    protected StockTakeReconciliationRepository reconRepository;

    protected PurchasingServiceImpl() {
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
    public List<PurchasingDetail> getDetails(int id) {
        return repository.getDetails(id);
    }

    @Override
    public Inventory getStockOnHand(LocalDate startDate, LocalDate endDate, LocalDate cutoffDate, Item item,
            Quality quality) {
        return reconRepository.getStockOnHand(startDate, endDate, cutoffDate, item, quality);
    }
}
