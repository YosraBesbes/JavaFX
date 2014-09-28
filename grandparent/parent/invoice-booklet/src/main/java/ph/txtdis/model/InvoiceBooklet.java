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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + endId;
        result = prime * result + ((issuedTo == null) ? 0 : issuedTo.hashCode());
        result = prime * result + startId;
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
        InvoiceBooklet other = (InvoiceBooklet) obj;
        if (endId != other.endId)
            return false;
        if (issuedTo == null) {
            if (other.issuedTo != null)
                return false;
        } else if (!issuedTo.equals(other.issuedTo))
            return false;
        if (startId != other.startId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return startId + "-" + endId + ": " + issuedTo + " on " + getTimeStamp();
    }
}
