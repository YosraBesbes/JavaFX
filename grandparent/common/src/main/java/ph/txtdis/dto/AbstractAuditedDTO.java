package ph.txtdis.dto;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractAudited;
import ph.txtdis.model.Users;
import ph.txtdis.service.Serviced;

@Component
public abstract class AbstractAuditedDTO<E extends AbstractAudited, S extends Serviced<E, Integer>> extends
        AbstractDTO<E, Serviced<E, Integer>, Integer> implements Audited<E> {

    @Autowired
    protected S service;

    public AbstractAuditedDTO() {
        super();
    }

    @Override
    public void set(E entity) {
        this.entity = entity;
        id = entity.getId();
    }

    @Override
    public Users getCreatedBy() {
        return entity.getCreatedBy();
    }

    @Override
    public ZonedDateTime getIssuedDate() {
        return entity.getCreatedDate();
    }
}
