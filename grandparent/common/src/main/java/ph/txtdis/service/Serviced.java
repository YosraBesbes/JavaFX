package ph.txtdis.service;

public interface Serviced<E> {

    boolean exists(int id);

    E get(int id);

    E save(E entity);

    void delete(E entity);
}