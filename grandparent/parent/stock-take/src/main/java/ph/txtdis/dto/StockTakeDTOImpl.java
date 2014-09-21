package ph.txtdis.dto;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Warehouse;
import ph.txtdis.service.StockTakeService;

@Component
public class StockTakeDTOImpl extends AbstractSpunByIdDTO<StockTake, StockTakeService> implements StockTakeDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new StockTake();
    }

    @Override
    public Warehouse getWarehouse() {
        return entity.getWarehouse();
    }

    @Override
    public void setWarehouse(Warehouse warehouse) {
        entity.setWarehouse(warehouse);
    }

    @Override
    public SystemUser getTaker() {
        return entity.getTaker();
    }

    @Override
    public void setTaker(SystemUser taker) {
        entity.setTaker(taker);
    }

    @Override
    public SystemUser getChecker() {
        return entity.getChecker();
    }

    @Override
    public void setChecker(SystemUser checker) {
        entity.setChecker(checker);
    }

    @Override
    public LocalDate getStockTakeDate() {
        return entity.getStockTakeDate();
    }

    @Override
    public void setStockTakeDate(LocalDate stockTakeDate) {
        entity.setStockTakeDate(stockTakeDate);
    }

    @Override
    public ObservableList<StockTakeDetail> getDetails() {
        return FXCollections.observableList(service.getDetails(id));
    }

    @Override
    public void setDetails(List<StockTakeDetail> details) {
        entity.setDetails(details);
    }

    @Override
    public LocalDate getLatestDate() {
        return service.getLatestDate();
    }
}
