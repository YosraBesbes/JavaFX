package ph.txtdis.service;

import java.time.LocalDate;

import ph.txtdis.model.StockTakeStatus;
import ph.txtdis.model.SystemUser;

public interface StockTakeStatusService extends DateService<StockTakeStatus> {

    SystemUser getClosedBy(LocalDate date);
}