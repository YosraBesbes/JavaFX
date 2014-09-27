package ph.txtdis.fx.tab;

import ph.txtdis.exception.TxtdisException;

public interface Edited {

    void save() throws TxtdisException;

    void refresh();
}
