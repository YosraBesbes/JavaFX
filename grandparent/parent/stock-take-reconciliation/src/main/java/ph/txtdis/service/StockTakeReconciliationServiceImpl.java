package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.ItemSummary;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;
import ph.txtdis.repository.StockTakeReconciliationRepository;

@Service
@Transactional()
public class StockTakeReconciliationServiceImpl extends AbstractDateService<StockTakeReconciliation> implements
        StockTakeReconciliationService {

    @Autowired
    private StockTakeReconciliationRepository repository;

    protected StockTakeReconciliationServiceImpl() {
    }

    @Override
    public LocalDate getOldestDate() {
        return repository.getOldestDate();
    }

    @Override
    public LocalDate getLatestDate() {
        return repository.getLatestDate();
    }

    @Override
    public List<ItemSummary> getItemSummary(LocalDate startDate, LocalDate endDate) {
        return repository.getItemSummary(startDate, endDate);
    }

    @Override
    public List<StockTakeReconciliationDetail> getDetails(LocalDate idDate) {
        return repository.getDetails(idDate);
    }
}
