package ph.txtdis.service;

import java.util.List;

public interface ListedServiced<E> extends Serviced<E> {
    List<E> list();
}
