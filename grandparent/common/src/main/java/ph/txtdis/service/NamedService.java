package ph.txtdis.service;

public interface NamedService<E> extends Serviced<E, Integer> {
    E get(String name);
}
