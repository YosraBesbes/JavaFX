package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public LocalDate getOldest() {
        return repository.getOldestDate();
    }

    @Override
    public LocalDate getLatestDate() {
        return repository.getLatestDate();
    }

    @Override
    public LocalDate getImmediatelyPrecedingDate(LocalDate date) {
        return repository.getImmediatelyPrecedingDate(date);
    }

    @Override
    public List<StockTakeReconciliationDetail> getStockTakeReconciliationDetail(LocalDate startDate, LocalDate endDate) {
        return repository.getDetail(startDate, endDate);
    }
}
