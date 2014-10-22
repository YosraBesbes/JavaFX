package ph.txtdis.dto;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;

import ph.txtdis.model.AbstractNamed;
import ph.txtdis.service.ListedNamedSearchedSpunService;

public abstract class AbstractListedNamedSearchedSpunDTO<E extends AbstractNamed, C, S extends ListedNamedSearchedSpunService<E, C>>
        extends AbstractSpunById<E, S> implements SearchedDTO<E, C>, ListedNamed<E> {

    @Autowired
    protected S service;
    protected E entity;
    protected ObservableList<E> list;

    public AbstractListedNamedSearchedSpunDTO() {
    }

    @Override
    public ObservableList<E> list() {
        return FXCollections.observableList(service.list());
    }

    @Override
    public ObservableList<E> findAll(C criteria) {
        setList(service.findAll(criteria));
        return getList();
    }

    @Override
    public ObservableList<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = FXCollections.observableList(list);
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public void setName(String name) {
        entity.setName(name);
    }

    @Override
    public E get(String name) {
        return service.get(name);
    }
}
