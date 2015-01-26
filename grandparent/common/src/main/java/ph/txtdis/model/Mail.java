package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Mail {

    Users getMailedBy();

    void setMailedBy(Users mailedBy);

    ZonedDateTime getMailedOn();

    void setMailedOn(ZonedDateTime mailedOn);
}
