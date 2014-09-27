package ph.txtdis.dto;

import java.time.ZonedDateTime;

import ph.txtdis.model.SystemUser;

public interface Printed {

    SystemUser getPrintedBy();

    void setPrintedBy(SystemUser printedBy);

    ZonedDateTime getPrintedOn();

    void setPrintedOn(ZonedDateTime printedOn);
}
