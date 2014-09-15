package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.ItemSummary;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;

public interface StockTakeReconciliationService extends SpunByDateService<StockTakeReconciliation> {

    List<StockTakeReconciliationDetail> getDetails(LocalDate idDate);

    List<ItemSummary> getItemSummary(LocalDate startDate, LocalDate endDate);
}