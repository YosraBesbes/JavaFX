package ph.txtdis.app;

import ph.txtdis.fx.tab.Edited;

public interface Apped extends Focused, Edited {

    void start();

    void save();

    void refresh();

    void setFocus();
}