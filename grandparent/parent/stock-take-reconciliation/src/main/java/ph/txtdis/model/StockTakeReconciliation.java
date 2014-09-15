package ph.txtdis.model;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import ph.txtdis.util.Util;

@Entity
public class StockTakeReconciliation extends AbstractDated {

    private static final long serialVersionUID = -7137997478152209732L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser closedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime closedOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser reconciledBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime reconciledOn;

    @OneToMany(mappedBy = "reconciliation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockTakeReconciliationDetail> details;

    public StockTakeReconciliation() {
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

    public List<StockTakeReconciliationDetail> getDetails() {
        return details;
    }

    public void setDetails(List<StockTakeReconciliationDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Stock Take on " + Util.formatLocalDate(getIdDate());
    }
}
