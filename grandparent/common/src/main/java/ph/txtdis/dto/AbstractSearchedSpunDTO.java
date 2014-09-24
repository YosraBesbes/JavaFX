package ph.txtdis.dto;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractAudited;
import ph.txtdis.service.SearchedSpunService;

@Component
public abstract class AbstractSearchedSpunDTO<E extends AbstractAudited, C, S extends SearchedSpunService<E, Integer, C>>
        extends AbstractSpunById<E, S> implements SearchedDTO<E, C> {

    protected ObservableList<E> list;

    @Override
    public ObservableList<E> findAll(C criteria) {
        setList(service.findAll(criteria));
        return getList();
    }

    @Override
    public ObservableList<E> getList() {
        return list;
    }

    @Override
    public void setList(List<E> list) {
        this.list = FXCollections.observableList(list);
    }
}
