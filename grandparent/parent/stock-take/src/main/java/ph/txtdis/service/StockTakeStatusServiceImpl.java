package ph.txtdis.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.StockTakeStatus;
import ph.txtdis.model.SystemUser;
import ph.txtdis.repository.StockTakeStatusRepository;

@Service
@Transactional()
public class StockTakeStatusServiceImpl extends AbstractDateService<StockTakeStatus> implements StockTakeStatusService {

    @Autowired
    private StockTakeStatusRepository repository;

    @Override
    public SystemUser getClosedBy(LocalDate date) {
        return repository.findOne(date).getClosedBy();
    }
}
