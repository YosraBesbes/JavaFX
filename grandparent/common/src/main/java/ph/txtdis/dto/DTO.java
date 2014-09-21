package ph.txtdis.dto;

public interface DTO<E> {

    void reset();

    void delete();

    void save();

    E get();

    void set(E entity);
}
