package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Send {

    SystemUser getSentBy();

    void setSentBy(SystemUser sentBy);

    ZonedDateTime getSentOn();

    void setSentOn(ZonedDateTime sentOn);
}
