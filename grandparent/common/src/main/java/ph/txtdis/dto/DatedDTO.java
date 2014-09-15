package ph.txtdis.dto;

import java.time.LocalDate;

public interface DatedDTO<E> {

    LocalDate getIdDate();

    void setByIdDate(LocalDate idDate);

    void reset();

    void delete();

    void save();

    E get();

    E get(LocalDate date);

    void set(E entity);

    boolean exists(LocalDate idDate);
}
