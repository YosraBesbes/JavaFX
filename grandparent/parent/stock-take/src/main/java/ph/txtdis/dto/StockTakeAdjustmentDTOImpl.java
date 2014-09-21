package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.StockTakeAdjustment;
import ph.txtdis.service.StockTakeAdjustmentService;

@Component
public class StockTakeAdjustmentDTOImpl extends AbstractAuditedDTO<StockTakeAdjustment, StockTakeAdjustmentService>
        implements StockTakeAdjustmentDTO {

    @Override
    public void reset() {
    }
}
