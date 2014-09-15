package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

import ph.txtdis.model.StockTakeStatus;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.StockTakeStatusService;

@Component
public class StockTakeStatusDTOImpl extends AbstractDatedDTO<StockTakeStatus, StockTakeStatusService> implements
        StockTakeStatusDTO {

    @Override
    public void reset() {
        idDate = null;
        entity = new StockTakeStatus();
    }

    public LocalDate getStockTakeDate() {
        return entity.getIdDate();
    }

    @Override
    public SystemUser getClosedBy() {
        return entity.getClosedBy();
    }

    @Override
    public ZonedDateTime getClosedOn() {
        return entity.getClosedOn();
    }
}
