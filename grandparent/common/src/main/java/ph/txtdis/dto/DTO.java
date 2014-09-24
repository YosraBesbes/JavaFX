package ph.txtdis.dto;

import ph.txtdis.service.Serviced;

public interface DTO<E, K> extends Serviced<E, K> {

    void reset();

    E get();

    void set(E entity);

    K getId();

    void save();

    void setById(K id);
}
