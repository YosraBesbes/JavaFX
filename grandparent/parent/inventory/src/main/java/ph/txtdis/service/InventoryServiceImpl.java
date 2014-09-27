package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Inventory;
import ph.txtdis.repository.StockTakeReconciliationRepository;

@Service
@Transactional()
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    protected StockTakeReconciliationRepository repository;

    protected InventoryServiceImpl() {
    }

    @Override
    public List<Inventory> getInventory(LocalDate startDate, LocalDate endDate) {
        return repository.getInventory(startDate, endDate, endDate.minusDays(28));
    }
}
