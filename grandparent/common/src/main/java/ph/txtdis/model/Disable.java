package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Disable {

    SystemUser getDisabledBy();

    void setDisabledBy(SystemUser disabledBy);

    ZonedDateTime getDisabledOn();

    void setDisabledOn(ZonedDateTime disabledOn);
}
