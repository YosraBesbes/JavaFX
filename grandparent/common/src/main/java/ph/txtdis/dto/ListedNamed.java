package ph.txtdis.dto;

public interface ListedNamed<E> extends Listed<E> {

    E get(String name);
}