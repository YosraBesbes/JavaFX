package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Receive {

    Users getReceivedBy();

    void setReceivedBy(Users receivedBy);

    ZonedDateTime getReceivedOn();

    void setReceivedOn(ZonedDateTime receivedOn);
}
