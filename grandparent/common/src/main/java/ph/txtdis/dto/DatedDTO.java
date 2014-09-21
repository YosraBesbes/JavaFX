package ph.txtdis.dto;

import java.time.LocalDate;

public interface DatedDTO<E> extends DTO<E> {

    LocalDate getIdDate();

    void setByIdDate(LocalDate idDate);

    E get(LocalDate date);

    boolean exists(LocalDate idDate);
}
