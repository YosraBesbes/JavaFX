package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.ItemDetailed;
import ph.txtdis.model.Ordered;

public interface OrderService<E extends Ordered<D>, D extends ItemDetailed> extends SpunService<E, Integer> {

    List<D> getDetails(int id);
}