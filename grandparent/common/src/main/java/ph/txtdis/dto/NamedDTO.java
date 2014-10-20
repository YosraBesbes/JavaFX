package ph.txtdis.dto;

public interface NamedDTO<E> {

    String getName();

    void setName(String name);

    E get(String name);
}