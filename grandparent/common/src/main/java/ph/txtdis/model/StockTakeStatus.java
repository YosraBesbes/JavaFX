package ph.txtdis.model;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

@Entity
public class StockTakeStatus extends AbstractDated {

    private static final long serialVersionUID = 6452482104413934317L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser closedBy;

    @Generated(value = GenerationTime.INSERT)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime closedOn;

    private boolean isClosed;

    private boolean isReconciled;

    public StockTakeStatus() {
    }

    public StockTakeStatus(SystemUser closedBy) {
        setClosedBy(closedBy);
        setClosed(true);
    }

    public SystemUser getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(SystemUser closedBy) {
        this.closedBy = closedBy;
    }

    public ZonedDateTime getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(ZonedDateTime closedOn) {
        this.closedOn = closedOn;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public boolean isReconciled() {
        return isReconciled;
    }

    public void setReconciled(boolean isReconciled) {
        this.isReconciled = isReconciled;
    }

    @Override
    public String toString() {
        return "Stock Take on " + getIdDate() + getReconciliationStatus();
    }

    private String getReconciliationStatus() {
        return isReconciled ? " is reconciled " : getClosureStatus();
    }

    private String getClosureStatus() {
        return isClosed ? " is closed" : "is on-going";
    }
}
