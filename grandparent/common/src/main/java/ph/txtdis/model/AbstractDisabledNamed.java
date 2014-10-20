package ph.txtdis.model;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public class AbstractDisabledNamed extends AbstractNamed implements Disable {

    private static final long serialVersionUID = 6409589135828964023L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected SystemUser disabledBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime disabledOn;

    protected AbstractDisabledNamed() {
    }

    public AbstractDisabledNamed(String name) {
        super(name);
    }

    @Override
    public SystemUser getDisabledBy() {
        return disabledBy;
    }

    @Override
    public void setDisabledBy(SystemUser disabledBy) {
        this.disabledBy = disabledBy;
    }

    @Override
    public ZonedDateTime getDisabledOn() {
        return disabledOn;
    }

    @Override
    public void setDisabledOn(ZonedDateTime disabledOn) {
        this.disabledOn = disabledOn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((disabledBy == null) ? 0 : disabledBy.hashCode());
        result = prime * result + ((disabledOn == null) ? 0 : disabledOn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractDisabledNamed other = (AbstractDisabledNamed) obj;
        if (disabledBy == null) {
            if (other.disabledBy != null)
                return false;
        } else if (!disabledBy.equals(other.disabledBy))
            return false;
        if (disabledOn == null) {
            if (other.disabledOn != null)
                return false;
        } else if (!disabledOn.equals(other.disabledOn))
            return false;
        return true;
    }
}