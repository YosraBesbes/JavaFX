package ph.txtdis.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Key;
import ph.txtdis.service.Serviced;

@Component
public abstract class AbstractDTO<E extends Key<K>, S extends Serviced<E, K>, K> implements DTO<E, K> {

    @Autowired
    protected S service;
    protected E entity;
    protected K id;

    public AbstractDTO() {
        reset();
    }

    @Override
    public K getId() {
        return id;
    }

    @Override
    public void setById(K id) {
        get(id);
    }

    @Override
    public boolean exists(K id) {
        return service.exists(id);
    }

    @Override
    public void save() {
        set(save(entity));
        setById(getId());
    }

    @Override
    public E save(E entity) {
        return service.save(entity);
    }

    @Override
    public E get() {
        if (entity == null)
            reset();
        return entity;
    }

    @Override
    public E get(K id) {
        entity = service.get(id);
        set(get());
        return entity;
    }

    @Override
    public void set(E entity) {
        this.entity = entity;
        id = entity.getId();
    }
}
