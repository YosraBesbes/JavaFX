package ph.txtdis.dto;

import javafx.collections.ObservableList;

public interface Listed<E> extends Named<E> {

    ObservableList<E> list();
}