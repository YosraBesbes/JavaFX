package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.model.StockTakeSummary;

public interface StockTakeService extends SpunByIdService<StockTake> {

    List<StockTakeDetail> getDetails(int id);

    List<StockTakeSummary> getSummary(LocalDate date);
}