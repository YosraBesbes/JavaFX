package ph.txtdis.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
public class Picking extends AbstractAudited {

    private static final long serialVersionUID = -3835242947594550479L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Truck truck;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private SystemUser driver;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private SystemUser helper1;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser helper2;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate pickDate;

    private String remarks;

    @OneToMany(mappedBy = "picking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PickingDetail> details;

    @Transient
    private List<PickList> pickList;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser printedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime printedOn;

    public Picking() {
    }

    public Picking(Truck truck, SystemUser driver, SystemUser helper1, LocalDate pickDate) {
        this.truck = truck;
        this.driver = driver;
        this.helper1 = helper1;
        this.pickDate = pickDate;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public SystemUser getDriver() {
        return driver;
    }

    public void setDriver(SystemUser driver) {
        this.driver = driver;
    }

    public SystemUser getHelper1() {
        return helper1;
    }

    public void setHelper1(SystemUser helper1) {
        this.helper1 = helper1;
    }

    public SystemUser getHelper2() {
        return helper2;
    }

    public void setHelper2(SystemUser helper2) {
        this.helper2 = helper2;
    }

    public LocalDate getPickDate() {
        return pickDate == null ? LocalDate.now() : pickDate;
    }

    public void setPickDate(LocalDate pickDate) {
        this.pickDate = pickDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<PickingDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PickingDetail> details) {
        this.details = details;
    }

    public SystemUser getPrintedBy() {
        return printedBy;
    }

    public void setPrintedBy(SystemUser printedBy) {
        this.printedBy = printedBy;
    }

    public ZonedDateTime getPrintedOn() {
        return printedOn;
    }

    public void setPrintedOn(ZonedDateTime printedOn) {
        this.printedOn = printedOn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((details == null) ? 0 : details.hashCode());
        result = prime * result + ((driver == null) ? 0 : driver.hashCode());
        result = prime * result + ((helper1 == null) ? 0 : helper1.hashCode());
        result = prime * result + ((helper2 == null) ? 0 : helper2.hashCode());
        result = prime * result + ((pickDate == null) ? 0 : pickDate.hashCode());
        result = prime * result + ((pickList == null) ? 0 : pickList.hashCode());
        result = prime * result + ((printedBy == null) ? 0 : printedBy.hashCode());
        result = prime * result + ((printedOn == null) ? 0 : printedOn.hashCode());
        result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
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
        Picking other = (Picking) obj;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        if (driver == null) {
            if (other.driver != null)
                return false;
        } else if (!driver.equals(other.driver))
            return false;
        if (helper1 == null) {
            if (other.helper1 != null)
                return false;
        } else if (!helper1.equals(other.helper1))
            return false;
        if (helper2 == null) {
            if (other.helper2 != null)
                return false;
        } else if (!helper2.equals(other.helper2))
            return false;
        if (pickDate == null) {
            if (other.pickDate != null)
                return false;
        } else if (!pickDate.equals(other.pickDate))
            return false;
        if (pickList == null) {
            if (other.pickList != null)
                return false;
        } else if (!pickList.equals(other.pickList))
            return false;
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
        if (remarks == null) {
            if (other.remarks != null)
                return false;
        } else if (!remarks.equals(other.remarks))
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
        return truck + " on " + pickDate;
    }
}
