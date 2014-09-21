package ph.txtdis.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractDated;
import ph.txtdis.service.SpunByDate;
import ph.txtdis.service.SpunByDateService;

@Component
public abstract class AbstractSpunByDateDTO<E extends AbstractDated, S extends SpunByDateService<E>> extends
        AbstractDatedDTO<E, S> implements SpunDTO, SpunByDate {

    @Override
    public void back() {
        System.err.println("at back");
        if (idDate != null && getOldest() != null) {
            if (idDate.isEqual(getOldest()))
                setByIdDate(getLatestDate());
            else if (exists(idDate.minusDays(1)))
                setByIdDate(idDate);
            else
                back();
        }
    }

    @Override
    public void next() {
        System.err.println("at next");
        if (idDate != null && getLatestDate() != null) {
            if (idDate.isEqual(getLatestDate()))
                setByIdDate(getOldest());
            else if (exists(idDate.plusDays(1)))
                setByIdDate(idDate);
            else
                next();
        }
    }

    @Override
    public LocalDate getOldest() {
        return service.getOldest();
    }

    @Override
    public LocalDate getLatestDate() {
        return service.getLatestDate();
    }
}
