package ph.txtdis.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "start_date" }))
public class Compensation extends AbstractAudited {

    private static final long serialVersionUID = -2803418471196037075L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "daily_rate", nullable = false)
    private BigDecimal dailyRate;

    protected Compensation() {
    }

    public Compensation(Employee employee, Date startDate, BigDecimal dailyRate) {
        this.employee = employee;
        this.startDate = startDate;
        this.dailyRate = dailyRate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    @Override
    public String toString() {
        return "[" + employee + "] " + startDate + ": " + dailyRate;
    }
}
