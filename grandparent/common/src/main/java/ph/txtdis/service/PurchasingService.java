package ph.txtdis.service;

import java.time.LocalDate;

import ph.txtdis.model.Inventory;
import ph.txtdis.model.Item;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
import ph.txtdis.model.Quality;

public interface PurchasingService extends OrderService<Purchasing, PurchasingDetail> {

    Inventory getStockOnHand(LocalDate startDate, LocalDate endDate, LocalDate cutoffDate, Item item, Quality quality);
}