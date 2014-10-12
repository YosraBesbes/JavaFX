package ph.txtdis.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;

import ph.txtdis.model.Named;
import ph.txtdis.service.ListedService;

public class AbstractListed<E extends Named, S extends ListedService<E>> implements Listed<E> {

    @Autowired
    protected S service;
    protected E entity;

    public AbstractListed() {
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
