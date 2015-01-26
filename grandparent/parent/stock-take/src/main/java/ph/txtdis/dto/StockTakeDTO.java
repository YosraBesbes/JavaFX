package ph.txtdis.dto;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.model.Users;
import ph.txtdis.model.Warehouse;

public interface StockTakeDTO extends Spun, Audited<StockTake> {

    public Warehouse getWarehouse();

    void setWarehouse(Warehouse warehouse);

    Users getTaker();

    void setTaker(Users taker);

    Users getChecker();

    void setChecker(Users checker);

    LocalDate getStockTakeDate();

    void setStockTakeDate(LocalDate stockTakeDate);

    ObservableList<StockTakeDetail> getDetails();

    void setDetails(List<StockTakeDetail> details);

    LocalDate getLatestDate();

    String getStockTakeAfter(LocalDate date);

    String getOneTransactionAfter(LocalDate date);

    String getClosureStatus(LocalDate date);

    String getOnGoingStockTakeStatus(LocalDate date);

    LocalDate getLatestCutoffDate();
}
