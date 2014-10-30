package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Cancel {

    SystemUser getCancelledBy();

    void setCancelledBy(SystemUser cancelledBy);

    ZonedDateTime getCancelledOn();

    void setCancelledOn(ZonedDateTime cancelledOn);
}
