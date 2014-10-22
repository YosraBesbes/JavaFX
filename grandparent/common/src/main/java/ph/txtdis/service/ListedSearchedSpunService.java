package ph.txtdis.service;

import java.util.List;

public interface ListedSearchedSpunService<E, C> extends SearchedSpunService<E, Integer, C> {

    List<E> list();
}
