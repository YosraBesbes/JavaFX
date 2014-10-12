package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.RemittanceSettlementAdjustment;

@Service
@Transactional()
public class RemittanceSettlementAdjustmentServiceImpl extends AbstractService<RemittanceSettlementAdjustment, Integer>
        implements RemittanceSettlementAdjustmentService {
}
