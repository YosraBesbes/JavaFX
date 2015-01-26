package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Send {

    Users getSentBy();

    void setSentBy(Users sentBy);

    ZonedDateTime getSentOn();

    void setSentOn(ZonedDateTime sentOn);
}
