package ph.txtdis.dto;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Inventory;
import ph.txtdis.service.InventoryService;
import ph.txtdis.service.StockTakeReconciliationService;
import ph.txtdis.util.Util;

@Component
public class InventoryDTOImpl implements InventoryDTO {

    @Autowired
    protected InventoryService service;

    @Autowired
    protected StockTakeReconciliationService reconService;

    @Override
    public String getStartDateText() {
        return Util.formatDate(reconService.getMaxId());
    }

    @Override
    public String getEndDateText() {
        return Util.formatDate(LocalDate.now());
    }

    @Override
    public ObservableList<Inventory> getInventoryList() {
        return FXCollections.observableList(service.getInventory(reconService.getMaxId(), LocalDate.now()));
    }
}
