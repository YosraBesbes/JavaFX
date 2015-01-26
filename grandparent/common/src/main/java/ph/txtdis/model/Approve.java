package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Approve {

    Users getApprovedBy();

    void setApprovedBy(Users approvedBy);

    ZonedDateTime getApprovedOn();

    void setApprovedOn(ZonedDateTime approvedOn);
}
