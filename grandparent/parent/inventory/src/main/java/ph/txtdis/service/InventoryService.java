package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.Inventory;

@Service
@Transactional()
public interface InventoryService {

    List<Inventory> getInventory(LocalDate startDate, LocalDate endDate);
}