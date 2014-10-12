package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.LoadSettlementAdjustment;
import ph.txtdis.service.LoadSettlementAdjustmentService;

@Component
public class LoadSettlementAdjustmentDTOImpl extends AbstractAuditedDTO<LoadSettlementAdjustment, LoadSettlementAdjustmentService>
        implements LoadSettlementAdjustmentDTO {

    @Override
    public void reset() {
    }
}
