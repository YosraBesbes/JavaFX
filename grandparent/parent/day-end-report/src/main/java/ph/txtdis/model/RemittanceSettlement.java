package ph.txtdis.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import ph.txtdis.util.Util;

@Entity
public class RemittanceSettlement extends AbstractAudited {

    private static final long serialVersionUID = 3725295775807671330L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Truck truck;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser reconciledBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime reconciledOn;

    public RemittanceSettlement() {
    }

    public RemittanceSettlement(Truck truck) {
        this.truck = truck;
    }

    public Truck getTruck() {
        return truck;
    }

    public LocalDate getDate() {
        return timeStamp == null ? null : timeStamp.toLocalDate();
    }

    public SystemUser getClosedBy() {
        return createdBy;
    }

    public ZonedDateTime getClosedOn() {
        return timeStamp;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((reconciledBy == null) ? 0 : reconciledBy.hashCode());
        result = prime * result + ((reconciledOn == null) ? 0 : reconciledOn.hashCode());
        result = prime * result + ((truck == null) ? 0 : truck.hashCode());
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
        RemittanceSettlement other = (RemittanceSettlement) obj;
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
        if (truck == null) {
            if (other.truck != null)
                return false;
        } else if (!truck.equals(other.truck))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return truck + " load settlement on " + Util.formatDate(getDate());
    }
}
