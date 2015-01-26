package ph.txtdis.dto;

import java.time.ZonedDateTime;

import ph.txtdis.model.AbstractDisabledNamed;
import ph.txtdis.model.Disable;
import ph.txtdis.model.Users;
import ph.txtdis.service.SearchedSpunService;

public abstract class AbstractDisabledSearchedSpunDTO<E extends AbstractDisabledNamed, C, S extends SearchedSpunService<E, Integer, C>>
        extends AbstractSearchedSpunDTO<E, C, S> implements Disable {

    public AbstractDisabledSearchedSpunDTO() {
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
        entity.setDisabledOn(disabledOn);
    }
}
