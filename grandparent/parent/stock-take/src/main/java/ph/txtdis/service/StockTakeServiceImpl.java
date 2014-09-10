package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.repository.StockTakeRepository;

@Service
@Transactional()
public class StockTakeServiceImpl extends AbstractService<StockTake> implements StockTakeService {

    @Autowired
    private StockTakeRepository repository;

    protected StockTakeServiceImpl() {
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
    public List<StockTakeDetail> getDetails(int id) {
        return repository.getDetails(id);
    }
}
