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
}
