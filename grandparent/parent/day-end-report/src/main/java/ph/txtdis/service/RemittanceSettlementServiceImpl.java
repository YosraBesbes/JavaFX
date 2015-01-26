package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.RemittanceSettlement;
import ph.txtdis.model.RemittanceSettlementDetail;
import ph.txtdis.model.Truck;
import ph.txtdis.repository.RemittanceSettlementRepository;
import ph.txtdis.util.Util;

@Service
@Transactional()
public class RemittanceSettlementServiceImpl extends AbstractService<RemittanceSettlement, Integer> implements
        RemittanceSettlementService {

    @Autowired
    private RemittanceSettlementRepository repository;

    protected RemittanceSettlementServiceImpl() {
    }

    @Override
    public List<RemittanceSettlementDetail> getDetail(Truck truck, LocalDate date) {
        return repository.getDetail(truck, date);
    }

    @Override
    public RemittanceSettlement get(Truck truck, LocalDate date) {
        return repository.findByTruckAndCreatedDateBetween(truck, Util.startOfDay(date), Util.endOfDay(date));
    }
}
