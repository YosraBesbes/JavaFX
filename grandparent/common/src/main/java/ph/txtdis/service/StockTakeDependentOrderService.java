package ph.txtdis.service;

import ph.txtdis.model.ItemDetailed;
import ph.txtdis.model.Ordered;

public interface StockTakeDependentOrderService<E extends Ordered<D>, D extends ItemDetailed> extends
        OrderService<E, D> {

    boolean isStockTakeOnGoing();
}