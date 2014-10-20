package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "startDate", "location", "designation" }))
public class Assignment extends AbstractAudited {

    private static final long serialVersionUID = 7017651807260423324L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate endDate;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String designation;

    protected Assignment() {
    }

    public Assignment(Employee employee, LocalDate startDate, String location, String designation) {
        this.employee = employee;
        this.startDate = startDate;
        this.location = location;
        this.designation = designation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "[" + employee + "] " + location + ": " + designation;
    }
}
