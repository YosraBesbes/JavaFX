package ph.txtdis.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ph.txtdis.model.AbstractNamed;
import ph.txtdis.service.ListedNamedService;

public abstract class AbstractListedNamedDTO<E extends AbstractNamed, S extends ListedNamedService<E>> extends
        AbstractAuditedDTO<E, S> implements ListedNamed<E> {

    public AbstractListedNamedDTO() {
    }

    @Override
    public E get(String name) {
        return service.get(name);
    }

    @Override
    public ObservableList<E> list() {
        return FXCollections.observableList(service.list());
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public void setName(String name) {
        entity.setName(name);
    }
}
