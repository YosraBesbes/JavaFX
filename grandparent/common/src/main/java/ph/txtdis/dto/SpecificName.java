package ph.txtdis.dto;

import ph.txtdis.model.Named;

public interface SpecificName<E> extends Named, Audited<E> {

    String getSpecific();
}