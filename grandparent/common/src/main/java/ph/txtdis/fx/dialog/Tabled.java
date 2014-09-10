package ph.txtdis.fx.dialog;

import java.util.List;

public interface Tabled<E, D> {
	List<E> getAddedItems(D dto);
}