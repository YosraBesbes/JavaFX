package ph.txtdis.fx.tab;

import ph.txtdis.exception.InvalidException;

public interface Edited {

    void save() throws InvalidException;

    void refresh();
}
