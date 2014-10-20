package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import ph.txtdis.type.LeaveType;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "type", "startDate" }))
public class Leave extends AbstractAudited {

    private static final long serialVersionUID = -3939404843675593545L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    private LeaveType type;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @Column(nullable = false)
    private int dayCount;

    protected Leave() {
    }

    public Leave(Employee employee, LeaveType type, LocalDate startDate, int dayCount) {
        this.employee = employee;
        this.type = type;
        this.startDate = startDate;
        this.dayCount = dayCount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }
}
