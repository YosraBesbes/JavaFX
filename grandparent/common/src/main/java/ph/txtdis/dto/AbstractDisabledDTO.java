package ph.txtdis.dto;

import java.time.ZonedDateTime;

import ph.txtdis.model.AbstractDisabledNamed;
import ph.txtdis.model.Disable;
import ph.txtdis.model.Users;
import ph.txtdis.service.ListedNamedService;

public abstract class AbstractDisabledDTO<E extends AbstractDisabledNamed, S extends ListedNamedService<E>> extends
        AbstractListedNamedDTO<E, S> implements ListedNamed<E>, Disable {

    protected AbstractDisabledDTO() {
    }

    @Override
    public Users getDisabledBy() {
        return entity.getDisabledBy();
    }

    @Override
    public void setDisabledBy(Users disabledBy) {
        entity.setDisabledBy(disabledBy);
    }

    @Override
    public ZonedDateTime getDisabledOn() {
        return entity.getDisabledOn();
    }

    @Override
    public void setDisabledOn(ZonedDateTime disabledOn) {
        setDisabledOn(disabledOn);
    }
}
