package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class InvoiceBooklet extends AbstractAudited {

    private static final long serialVersionUID = 6045289585003677813L;

    @Column(nullable = false, unique = true)
    private int startId;

    @Column(nullable = false, unique = true)
    private int endId;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private SystemUser issuedTo;
    
    public InvoiceBooklet() {
    }

    public InvoiceBooklet(int startId, int endId, SystemUser issuedTo) {
        this.startId = startId;
        this.endId = endId;
        this.issuedTo = issuedTo;
    }

    public int getStartId() {
        return startId;
    }

    public void setStartId(int startId) {
        this.startId = startId;
    }

    public int getEndId() {
        return endId;
    }

    public void setEndId(int endId) {
        this.endId = endId;
    }

    public SystemUser getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(SystemUser issuedTo) {
        this.issuedTo = issuedTo;
    }

    @Override
    public String toString() {
        return startId + "-" + endId + ": " + issuedTo + " on " + getTimeStamp();
    }
}
