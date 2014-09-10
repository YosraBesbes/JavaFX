package ph.txtdis.dto;

import java.util.List;

public interface SearchedDTO<E, C> {

    List<E> findAll(C criteria);

    List<E> getList();

    void setList(List<E> list);
}
