package ph.txtdis.dto;

import java.time.ZonedDateTime;

import ph.txtdis.model.StockTakeStatus;
import ph.txtdis.model.SystemUser;

public interface StockTakeStatusDTO extends DatedDTO<StockTakeStatus> {

    SystemUser getClosedBy();

    ZonedDateTime getClosedOn();
}
