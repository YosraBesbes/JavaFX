package ph.txtdis.dto;

import java.util.List;

public interface Detailed<E, D> extends DTO<E, Integer> {

    List<D> getDetails();

    void setDetails(List<D> details);
}