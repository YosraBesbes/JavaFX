package ph.txtdis.app;

import ph.txtdis.exception.InvalidException;

public interface Printed extends Apped {

    void print() throws InvalidException;
}
