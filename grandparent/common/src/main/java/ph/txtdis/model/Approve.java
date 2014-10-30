package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Approve {

    SystemUser getApprovedBy();

    void setApprovedBy(SystemUser approvedBy);

    ZonedDateTime getApprovedOn();

    void setApprovedOn(ZonedDateTime approvedOn);
}
