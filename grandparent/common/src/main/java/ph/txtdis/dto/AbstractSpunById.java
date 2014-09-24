package ph.txtdis.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractAudited;
import ph.txtdis.service.SpunService;

@Component
public abstract class AbstractSpunById<E extends AbstractAudited, S extends SpunService<E, Integer>> extends
        AbstractAuditedDTO<E, S> implements Spun {

    @Autowired
    protected S service;

    @Override
    public void back() {
        if (id == 0 || id == service.getMinId())
            setById(service.getMaxId());
        else if (exists(--id))
            setById(id);
        else
            back();
    }

    @Override
    public void next() {
        int max = service.getMaxId();
        if (id == max && max != 0)
            setById(service.getMinId());
        else if (exists(++id))
            setById(id);
        else if (max != 0)
            next();
    }
}
