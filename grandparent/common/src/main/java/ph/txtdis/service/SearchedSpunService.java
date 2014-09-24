package ph.txtdis.service;

import java.util.List;

public interface SearchedSpunService<E, K, C> extends SpunService<E, K> {

    List<E> findAll(C criteria);
}
