package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Receive {

    SystemUser getReceivedBy();

    void setReceivedBy(SystemUser receivedBy);

    ZonedDateTime getReceivedOn();

    void setReceivedOn(ZonedDateTime receivedOn);
}
