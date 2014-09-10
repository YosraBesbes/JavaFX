package ph.txtdis.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;

import ph.txtdis.model.Typed;
import ph.txtdis.service.ListedServiced;

public class AbstractTypedDTO<E extends Typed, S extends ListedServiced<E>> implements TypedDTO<E> {

    @Autowired
    protected S service;
    protected E entity;

    public AbstractTypedDTO() {
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
