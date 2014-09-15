package ph.txtdis.service;

import java.util.List;

public interface ListedServiced<E> extends IdService<E> {
    List<E> list();
}
