package ph.txtdis.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractDated;
import ph.txtdis.service.SpunByDateService;

@Component
public abstract class AbstractSpunByDateDTO<E extends AbstractDated, S extends SpunByDateService<E>> extends
        AbstractDatedDTO<E, S> implements SpunDTO {

    @Override
    public void back() {
        if (idDate != null || getOldest() != null) {
            if (idDate.isEqual(getOldest()))
                setByIdDate(getLatest());
            else if (exists(idDate.minusDays(1)))
                setByIdDate(idDate);
            else
                back();
        }
    }

    @Override
    public void next() {
        if (idDate != null || getLatest() != null) {
            if (idDate.isEqual(getLatest()))
                setByIdDate(getOldest());
            else if (exists(idDate.plusDays(1)))
                setByIdDate(idDate);
            else
                next();
        }
    }

    private LocalDate getOldest() {
        return service.getOldestDate();
    }

    private LocalDate getLatest() {
        return service.getLatestDate();
    }
}
