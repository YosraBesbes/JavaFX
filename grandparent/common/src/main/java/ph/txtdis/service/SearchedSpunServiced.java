package ph.txtdis.service;

import java.util.List;

public interface SearchedSpunServiced<E, C> extends SpunServiced<E> {
    List<E> findAll(C criteria);
}
