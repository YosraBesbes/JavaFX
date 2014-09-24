package ph.txtdis.service;

import java.util.List;

public interface ListedService<E> extends Serviced<E, Integer> {
    List<E> list();
}
