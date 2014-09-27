package ph.txtdis.printer;

import ph.txtdis.exception.TxtdisException;

public class NotPrintedException extends TxtdisException {

    private static final long serialVersionUID = 7010664033441991775L;

    public NotPrintedException() {
        super("Not all were printed;\ncheck every connections, then restart");
    }
}
