package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class Picking extends AbstractAudited {

    private static final long serialVersionUID = 2202349518592149416L;

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
        return pickDate;
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

    @Override
    public String toString() {
        return truck + " on " + pickDate;
    }
}
