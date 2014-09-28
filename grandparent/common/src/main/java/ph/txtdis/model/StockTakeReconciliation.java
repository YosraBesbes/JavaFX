package ph.txtdis.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import ph.txtdis.util.Util;

@Entity
public class StockTakeReconciliation extends AbstractDated {

    private static final long serialVersionUID = -7137997478152209732L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser cutoffBy;

    @Column(columnDefinition = "timestamp with time zone DEFAULT current_timestamp", updatable = false,
            insertable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime cutoffOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser closedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime closedOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser reconciledBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime reconciledOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser mailedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime mailedOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser approvedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime approvedOn;

    private Boolean isApproved;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser completedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime completedOn;

    public StockTakeReconciliation() {
    }

    public StockTakeReconciliation(SystemUser cutoffBy, LocalDate id) {
        setCutoffBy(cutoffBy);
        setId(id);
    }

    public SystemUser getCutoffBy() {
        return cutoffBy;
    }

    public void setCutoffBy(SystemUser cutoffBy) {
        this.cutoffBy = cutoffBy;
    }

    public ZonedDateTime getCutoffOn() {
        return cutoffOn;
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

    public SystemUser getReconciledBy() {
        return reconciledBy;
    }

    public void setReconciledBy(SystemUser reconciledBy) {
        this.reconciledBy = reconciledBy;
    }

    public ZonedDateTime getReconciledOn() {
        return reconciledOn;
    }

    public void setReconciledOn(ZonedDateTime reconciledOn) {
        this.reconciledOn = reconciledOn;
    }

    public SystemUser getMailedBy() {
        return mailedBy;
    }

    public void setMailedBy(SystemUser mailedBy) {
        this.mailedBy = mailedBy;
    }

    public ZonedDateTime getMailedOn() {
        return mailedOn;
    }

    public void setMailedOn(ZonedDateTime mailedOn) {
        this.mailedOn = mailedOn;
    }

    public SystemUser getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(SystemUser approvedBy) {
        this.approvedBy = approvedBy;
    }

    public ZonedDateTime getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(ZonedDateTime approvedOn) {
        this.approvedOn = approvedOn;
    }

    public Boolean isApproved() {
        return isApproved;
    }

    public void setApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public SystemUser getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(SystemUser completedBy) {
        this.completedBy = completedBy;
    }

    public ZonedDateTime getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(ZonedDateTime completedOn) {
        this.completedOn = completedOn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
        result = prime * result + ((approvedOn == null) ? 0 : approvedOn.hashCode());
        result = prime * result + ((closedBy == null) ? 0 : closedBy.hashCode());
        result = prime * result + ((closedOn == null) ? 0 : closedOn.hashCode());
        result = prime * result + ((completedBy == null) ? 0 : completedBy.hashCode());
        result = prime * result + ((completedOn == null) ? 0 : completedOn.hashCode());
        result = prime * result + ((cutoffBy == null) ? 0 : cutoffBy.hashCode());
        result = prime * result + ((cutoffOn == null) ? 0 : cutoffOn.hashCode());
        result = prime * result + ((isApproved == null) ? 0 : isApproved.hashCode());
        result = prime * result + ((mailedBy == null) ? 0 : mailedBy.hashCode());
        result = prime * result + ((mailedOn == null) ? 0 : mailedOn.hashCode());
        result = prime * result + ((reconciledBy == null) ? 0 : reconciledBy.hashCode());
        result = prime * result + ((reconciledOn == null) ? 0 : reconciledOn.hashCode());
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
        StockTakeReconciliation other = (StockTakeReconciliation) obj;
        if (approvedBy == null) {
            if (other.approvedBy != null)
                return false;
        } else if (!approvedBy.equals(other.approvedBy))
            return false;
        if (approvedOn == null) {
            if (other.approvedOn != null)
                return false;
        } else if (!approvedOn.equals(other.approvedOn))
            return false;
        if (closedBy == null) {
            if (other.closedBy != null)
                return false;
        } else if (!closedBy.equals(other.closedBy))
            return false;
        if (closedOn == null) {
            if (other.closedOn != null)
                return false;
        } else if (!closedOn.equals(other.closedOn))
            return false;
        if (completedBy == null) {
            if (other.completedBy != null)
                return false;
        } else if (!completedBy.equals(other.completedBy))
            return false;
        if (completedOn == null) {
            if (other.completedOn != null)
                return false;
        } else if (!completedOn.equals(other.completedOn))
            return false;
        if (cutoffBy == null) {
            if (other.cutoffBy != null)
                return false;
        } else if (!cutoffBy.equals(other.cutoffBy))
            return false;
        if (cutoffOn == null) {
            if (other.cutoffOn != null)
                return false;
        } else if (!cutoffOn.equals(other.cutoffOn))
            return false;
        if (isApproved == null) {
            if (other.isApproved != null)
                return false;
        } else if (!isApproved.equals(other.isApproved))
            return false;
        if (mailedBy == null) {
            if (other.mailedBy != null)
                return false;
        } else if (!mailedBy.equals(other.mailedBy))
            return false;
        if (mailedOn == null) {
            if (other.mailedOn != null)
                return false;
        } else if (!mailedOn.equals(other.mailedOn))
            return false;
        if (reconciledBy == null) {
            if (other.reconciledBy != null)
                return false;
        } else if (!reconciledBy.equals(other.reconciledBy))
            return false;
        if (reconciledOn == null) {
            if (other.reconciledOn != null)
                return false;
        } else if (!reconciledOn.equals(other.reconciledOn))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Stock Take on " + Util.formatDate(getId());
    }
}
