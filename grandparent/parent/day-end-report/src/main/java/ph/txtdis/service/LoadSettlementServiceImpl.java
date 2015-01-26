package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.LoadSettlement;
import ph.txtdis.model.LoadSettlementDetail;
import ph.txtdis.model.Truck;
import ph.txtdis.repository.LoadSettlementRepository;
import ph.txtdis.util.Util;

@Service
@Transactional()
public class LoadSettlementServiceImpl extends AbstractService<LoadSettlement, Integer> implements
        LoadSettlementService {

    @Autowired
    private LoadSettlementRepository repository;

    protected LoadSettlementServiceImpl() {
    }

    @Override
    public List<LoadSettlementDetail> getDetail(Truck truck, LocalDate date) {
        return repository.getDetail(truck, date);
    }

    @Override
    public LoadSettlement get(Truck truck, LocalDate date) {
        return repository.findByTruckAndCreatedDateBetween(truck, Util.startOfDay(date), Util.endOfDay(date));
    }
}