package ph.txtdis.service;

public interface IdService<E> {

    boolean exists(int id);

    E get(int id);

    E save(E entity);

    void delete(E entity);
}