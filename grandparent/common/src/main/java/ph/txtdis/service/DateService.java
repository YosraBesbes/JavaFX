package ph.txtdis.service;

import java.time.LocalDate;

public interface DateService<E> {

    boolean exists(LocalDate idDate);

    E get(LocalDate idDate);

    E save(E entity);

    void delete(E entity);
}