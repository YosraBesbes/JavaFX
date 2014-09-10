package ph.txtdis.dto;

import javafx.collections.ObservableList;

public interface TypedDTO<E> extends NamedDTO {
    
    ObservableList<E> list();

    void setName(String name);
}