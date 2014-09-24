package ph.txtdis.dto;

public interface SpecificName<E> extends Named, Audited<E> {

    String getSpecific();
}