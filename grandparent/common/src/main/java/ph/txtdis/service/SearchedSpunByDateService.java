package ph.txtdis.service;

import java.util.List;

public interface SearchedSpunByDateService<E, C> extends SpunByDateService<E> {
    List<E> findAll(C criteria);
}
