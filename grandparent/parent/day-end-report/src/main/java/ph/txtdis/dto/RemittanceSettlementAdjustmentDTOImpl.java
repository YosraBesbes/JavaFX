package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.RemittanceSettlementAdjustment;
import ph.txtdis.service.RemittanceSettlementAdjustmentService;

@Component
public class RemittanceSettlementAdjustmentDTOImpl extends
        AbstractAuditedDTO<RemittanceSettlementAdjustment, RemittanceSettlementAdjustmentService> implements
        RemittanceSettlementAdjustmentDTO {

    @Override
    public void reset() {
    }
}
