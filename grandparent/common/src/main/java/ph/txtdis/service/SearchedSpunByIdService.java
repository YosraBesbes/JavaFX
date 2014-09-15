package ph.txtdis.service;

import java.util.List;

public interface SearchedSpunByIdService<E, C> extends SpunByIdService<E> {
    List<E> findAll(C criteria);
}
