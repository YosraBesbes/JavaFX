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
        if (id == 0 || id == service.getMinId()) {
            setByIdIfDifferent(id, service.getMaxId());
        } else if (noGaps(--id))
            setById(id);
        else
            back();
    }

    @Override
    public void next() {
        if (id == service.getMaxId())
            setByIdIfDifferent(id, service.getMinId());
        else if (noGaps(++id))
            setById(id);
        else
            next();
    }

    private void setByIdIfDifferent(int id1, int id2) {
        if (id1 != id2)
            setById(service.getMaxId());
    }

    private boolean noGaps(int id) {
        return exists(id);
    }
}
