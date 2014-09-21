package ph.txtdis.dto;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractAudited;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.IdService;
import ph.txtdis.util.Login;

@Component
public abstract class AbstractAuditedDTO<E extends AbstractAudited, S extends IdService<E>> implements AuditedDTO<E> {

    @Autowired
    protected S service;
    protected E entity;
    protected int id;

    public AbstractAuditedDTO() {
        reset();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        set(service.get(id));
    }

    @Override
    public boolean exists(int id) {
        return service.exists(id);
    }

    @Override
    public void delete() {
        service.delete(entity);
        reset();
    }

    @Override
    public void save() {
        entity.setCreatedBy(Login.user());
        set(service.save(entity));
        setId(getId());
    }

    @Override
    public E get() {
        if (entity == null)
            reset();
        return entity;
    }

    @Override
    public E get(int id) {
        set(service.get(id));
        return entity;
    }

    @Override
    public void set(E entity) {
        this.entity = entity;
        id = entity.getId();
    }

    @Override
    public SystemUser getCreatedBy() {
        return entity.getCreatedBy();
    }

    @Override
    public void setCreatedBy(SystemUser createdBy) {
        entity.setCreatedBy(createdBy);
    }

    @Override
    public ZonedDateTime getTimeStamp() {
        return entity.getTimeStamp();
    }

    @Override
    public void setTimeStamp(ZonedDateTime timeStamp) {
        entity.setTimeStamp(timeStamp);
    }
}
