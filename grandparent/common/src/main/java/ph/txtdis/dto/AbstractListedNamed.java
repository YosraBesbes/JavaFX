package ph.txtdis.dto;

import ph.txtdis.model.Named;
import ph.txtdis.service.ListedNamedService;

public class AbstractListedNamed<E extends Named, S extends ListedNamedService<E>> extends AbstractListed<E, S>
        implements ListedNamed<E> {

    public AbstractListedNamed() {
    }

    @Override
    public E get(String name) {
        return service.get(name);
    }
}
