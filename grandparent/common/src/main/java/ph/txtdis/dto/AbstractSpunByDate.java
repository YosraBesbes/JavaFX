package ph.txtdis.dto;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractDated;
import ph.txtdis.service.MinMax;
import ph.txtdis.service.SpunService;

@Component
public abstract class AbstractSpunByDate<E extends AbstractDated, S extends SpunService<E, LocalDate>> extends
        AbstractDTO<E, S, LocalDate> implements Spun, MinMax<LocalDate> {

    @Autowired
    protected S service;

    @Override
    public void back() {
        if (id != null && getMinId() != null) {
            if (id.isEqual(getMinId()))
                setById(getMaxId());
            else if (exists(id.minusDays(1)))
                setById(id);
            else
                back();
        }
    }

    @Override
    public void next() {
        if (id != null && getMaxId() != null) {
            if (id.isEqual(getMaxId()))
                setById(getMinId());
            else if (exists(id.plusDays(1)))
                setById(id);
            else
                next();
        }
    }

    @Override
    public LocalDate getMinId() {
        return service.getMinId();
    }

    @Override
    public LocalDate getMaxId() {
        return service.getMaxId();
    }
}
