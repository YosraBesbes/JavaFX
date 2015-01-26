package ph.txtdis.model;

import java.time.ZonedDateTime;

public interface Disable {

    Users getDisabledBy();

    void setDisabledBy(Users disabledBy);

    ZonedDateTime getDisabledOn();

    void setDisabledOn(ZonedDateTime disabledOn);
}
