package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.Inventory;

public interface InventoryService {

    List<Inventory> getInventory(LocalDate startDate, LocalDate endDate);
}