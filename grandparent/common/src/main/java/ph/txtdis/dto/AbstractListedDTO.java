package ph.txtdis.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;

import ph.txtdis.model.AbstractAudited;
import ph.txtdis.service.ListedService;

public abstract class AbstractListedDTO<E extends AbstractAudited, S extends ListedService<E>> extends
        AbstractAuditedDTO<E, S> implements Listed<E> {

    @Autowired
    protected S service;
    protected E entity;

    public AbstractListedDTO() {
    }

    @Override
    public ObservableList<E> list() {
        return FXCollections.observableList(service.list());
    }
}
