package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;

public interface StockTakeService extends SpunByIdService<StockTake> {

    List<StockTakeDetail> getDetails(int id);

    LocalDate getLatestDate();
}