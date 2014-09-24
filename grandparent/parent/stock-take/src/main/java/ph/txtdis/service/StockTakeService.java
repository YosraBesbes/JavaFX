package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;

public interface StockTakeService extends SpunService<StockTake, Integer> {

    List<StockTakeDetail> getDetails(int id);

    LocalDate getLatestDate();

    String getStockTakeAfter(LocalDate date);

    String getOneTransactionAfter(LocalDate date);

    String getClosureStamp(LocalDate date);

    String getOnGoingStockTake(LocalDate date);

    LocalDate getLatestCutoffDate();
}