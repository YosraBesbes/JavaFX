package ph.txtdis.model;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

import ph.txtdis.dto.Printed;

@MappedSuperclass
public abstract class AbstractPrintedOrder<D extends ItemDetailed> extends AbstractOrder<D> implements Printed {

    private static final long serialVersionUID = 4553686125204430154L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser printedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime printedOn;

    public AbstractPrintedOrder() {
    }

    @Override
    public SystemUser getPrintedBy() {
        return printedBy;
    }

    @Override
    public void setPrintedBy(SystemUser printedBy) {
        this.printedBy = printedBy;
    }

    @Override
    public ZonedDateTime getPrintedOn() {
        return printedOn;
    }

    @Override
    public void setPrintedOn(ZonedDateTime printedOn) {
        this.printedOn = printedOn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((printedBy == null) ? 0 : printedBy.hashCode());
        result = prime * result + ((printedOn == null) ? 0 : printedOn.hashCode());
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
        AbstractPrintedOrder<?> other = (AbstractPrintedOrder<?>) obj;
        if (printedBy == null) {
            if (other.printedBy != null)
                return false;
        } else if (!printedBy.equals(other.printedBy))
            return false;
        if (printedOn == null) {
            if (other.printedOn != null)
                return false;
        } else if (!printedOn.equals(other.printedOn))
            return false;
        return true;
    }
}
