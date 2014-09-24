package ph.txtdis.dto;

import javafx.collections.ObservableList;

public interface ListedNamed<E> extends Named {
    
    ObservableList<E> list();
}