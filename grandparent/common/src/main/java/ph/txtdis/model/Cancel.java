package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Cancel {

    Users getCancelledBy();

    void setCancelledBy(Users cancelledBy);

    ZonedDateTime getCancelledOn();

    void setCancelledOn(ZonedDateTime cancelledOn);
}
