package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;

public interface StockTakeReconciliationService extends SpunService<StockTakeReconciliation, LocalDate> {

    List<StockTakeReconciliationDetail> getDetail(LocalDate startDate, LocalDate endDate);

    LocalDate getImmediatelyPrecedingDate(LocalDate date);
}