package ph.txtdis.dto;

import javafx.collections.ObservableList;

public interface Listed<E> {

    ObservableList<E> list();
}