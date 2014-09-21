package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;

public interface StockTakeReconciliationService extends SpunByDateService<StockTakeReconciliation> {

    List<StockTakeReconciliationDetail> getStockTakeReconciliationDetail(LocalDate startDate, LocalDate endDate);

    LocalDate getImmediatelyPrecedingDate(LocalDate date);
}