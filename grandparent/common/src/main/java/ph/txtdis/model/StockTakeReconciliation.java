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
    public String toString() {
        return "Stock Take on " + Util.formatDate(getId());
    }
}
