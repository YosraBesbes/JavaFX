package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Mail {

    SystemUser getMailedBy();

    void setMailedBy(SystemUser mailedBy);

    ZonedDateTime getMailedOn();

    void setMailedOn(ZonedDateTime mailedOn);
}
