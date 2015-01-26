package ph.txtdis.dto;

import java.time.ZonedDateTime;

import ph.txtdis.model.Users;

public interface Printed {

    Users getPrintedBy();

    void setPrintedBy(Users user);

    ZonedDateTime getPrintedOn();

    void setPrintedOn(ZonedDateTime printedOn);
}
