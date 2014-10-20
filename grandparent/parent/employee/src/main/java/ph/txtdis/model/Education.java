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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "startDate", "institution", "program" }))
public class Education extends AbstractAudited {

    private static final long serialVersionUID = -314345074933355267L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate endDate;

    @Column(length = 16, nullable = false)
    private String institution;

    @Column(length = 16, nullable = false)
    private String program;

    @Column(length = 16, nullable = false)
    private String highestHonor;

    protected Education() {
    }

    public Education(Employee employee, LocalDate startDate, LocalDate endDate, String institution, String program,
            String highestHonor) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.institution = institution;
        this.program = program;
        this.highestHonor = highestHonor;
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

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getHighestHonor() {
        return highestHonor;
    }

    public void setHighestHonor(String highestHonor) {
        this.highestHonor = highestHonor;
    }

    @Override
    public String toString() {
        return "[" + employee + "] " + institution + ": " + program;
    }
}
