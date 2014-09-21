package ph.txtdis.dto;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Warehouse;

public interface StockTakeDTO extends SpunDTO, AuditedDTO<StockTake> {

    public Warehouse getWarehouse();

    void setWarehouse(Warehouse warehouse);

    SystemUser getTaker();

    void setTaker(SystemUser taker);

    SystemUser getChecker();

    void setChecker(SystemUser checker);

    LocalDate getStockTakeDate();

    void setStockTakeDate(LocalDate stockTakeDate);

    ObservableList<StockTakeDetail> getDetails();

    void setDetails(List<StockTakeDetail> details);

    LocalDate getLatestDate();
}
