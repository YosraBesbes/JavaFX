package ph.txtdis.service;

public interface Serviced<E, K> {

    boolean exists(K id);

    E get(K id);

    E save(E entity);
}