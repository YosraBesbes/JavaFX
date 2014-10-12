package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.LoadSettlementAdjustment;

@Service
@Transactional()
public class LoadSettlementAdjustmentServiceImpl extends AbstractService<LoadSettlementAdjustment, Integer> implements
        LoadSettlementAdjustmentService {
}
