package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.ItemDetailed;
import ph.txtdis.model.Ordered;

public interface OrderServiced<E extends Ordered<D>, D extends ItemDetailed> extends SpunServiced<E> {
    
    List<D> getDetails(int id);
}