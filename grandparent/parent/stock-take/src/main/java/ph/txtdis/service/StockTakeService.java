package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;

public interface StockTakeService extends SpunServiced<StockTake> {
    
    List<StockTakeDetail> getDetails(int id);
}