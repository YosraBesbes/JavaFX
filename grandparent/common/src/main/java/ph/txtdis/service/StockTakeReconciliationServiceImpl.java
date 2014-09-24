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
public class StockTakeReconciliationServiceImpl extends AbstractService<StockTakeReconciliation, LocalDate> implements
        StockTakeReconciliationService {

    @Autowired
    private StockTakeReconciliationRepository repository;

    protected StockTakeReconciliationServiceImpl() {
    }

    @Override
    public LocalDate getMinId() {
        return repository.getOldestDate();
    }

    @Override
    public LocalDate getMaxId() {
        return repository.getLatestDate();
    }

    @Override
    public LocalDate getImmediatelyPrecedingDate(LocalDate date) {
        return repository.getImmediatelyPrecedingDate(date);
    }

    @Override
    public List<StockTakeReconciliationDetail> getDetail(LocalDate startDate, LocalDate endDate) {
        return repository.getDetail(startDate, endDate);
    }
}
