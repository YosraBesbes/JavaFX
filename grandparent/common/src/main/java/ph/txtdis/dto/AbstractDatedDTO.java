package ph.txtdis.dto;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractDated;
import ph.txtdis.service.DateService;

@Component
public abstract class AbstractDatedDTO<E extends AbstractDated, S extends DateService<E>> implements DatedDTO<E> {

    @Autowired
    protected S service;
    protected E entity;
    protected LocalDate idDate;

    public AbstractDatedDTO() {
        reset();
    }

    @Override
    public LocalDate getIdDate() {
        return idDate;
    }

    @Override
    public void setByIdDate(LocalDate idDate) {
        get(idDate);
    }

    @Override
    public boolean exists(LocalDate idDate) {
        return service.exists(idDate);
    }

    @Override
    public void delete() {
        service.delete(entity);
        reset();
    }

    @Override
    public void save() {
        set(service.save(entity));
    }

    @Override
    public E get() {
        if (entity == null)
            reset();
        return entity;
    }

    @Override
    public E get(LocalDate idDate) {
        entity = service.get(idDate);
        set(get());
        return entity;
    }

    @Override
    public void set(E entity) {
        this.entity = entity;
        idDate = entity.getIdDate();
    }
}
