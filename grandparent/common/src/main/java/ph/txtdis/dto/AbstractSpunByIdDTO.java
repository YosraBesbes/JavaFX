package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractAudited;
import ph.txtdis.service.SpunByIdService;

@Component
public abstract class AbstractSpunByIdDTO<E extends AbstractAudited, S extends SpunByIdService<E>> extends
        AbstractAuditedDTO<E, S> implements SpunDTO {

    @Override
    public void back() {
        if (id == 0 || id == service.getMinId())
            setId(service.getMaxId());
        else if (exists(--id))
            setId(id);
        else
            back();
    }

    @Override
    public void next() {
        int max = service.getMaxId();
        if (id == max && max != 0)
            setId(service.getMinId());
        else if (exists(++id))
            setId(id);
        else if (max != 0)
            next();
    }
}
