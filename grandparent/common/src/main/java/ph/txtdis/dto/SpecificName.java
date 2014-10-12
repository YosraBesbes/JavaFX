package ph.txtdis.dto;

public interface SpecificName<E> extends Named<E>, Audited<E> {

    String getSpecific();
}