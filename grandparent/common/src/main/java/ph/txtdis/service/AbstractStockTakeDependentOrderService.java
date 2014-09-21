package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.ItemDetailed;
import ph.txtdis.model.Ordered;
import ph.txtdis.repository.StockTakeReconciliationRepository;

@Service
@Transactional()
public abstract class AbstractStockTakeDependentOrderService<E extends Ordered<D>, D extends ItemDetailed> extends
        AbstractIdService<E> implements StockTakeDependentOrderService<E, D> {

    @Autowired
    private StockTakeReconciliationRepository repository;

    protected AbstractStockTakeDependentOrderService() {
    }

    @Override
    public boolean isStockTakeOnGoing() {
        return repository.getLatestApprover() != null;
    }
}
