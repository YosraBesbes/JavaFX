package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "startDate", "employer", "designation" }),
        name = "past_work")
public class PastWork extends AbstractAudited {

    private static final long serialVersionUID = 4972100444095311026L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate endDate;

    @Column(nullable = false)
    private String employer;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private BigDecimal lastPay;

    @Column(nullable = false)
    private String reasonForLeaving;

    @Column(nullable = false)
    private String referenceName;

    @Column(nullable = false)
    private String referenceDesignation;

    @Column(nullable = false)
    private String referencePhone;

    protected PastWork() {
    }

    public PastWork(Employee employee, LocalDate startDate, LocalDate endDate, String employer, String designation,
            BigDecimal lastPay, String reasonForLeaving, String referenceName, String referenceDesignation,
            String referencePhone) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employer = employer;
        this.designation = designation;
        this.lastPay = lastPay;
        this.reasonForLeaving = reasonForLeaving;
        this.referenceName = referenceName;
        this.referenceDesignation = referenceDesignation;
        this.referencePhone = referencePhone;
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

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getLastPay() {
        return lastPay;
    }

    public void setLastPay(BigDecimal lastPay) {
        this.lastPay = lastPay;
    }

    public String getReasonForLeaving() {
        return reasonForLeaving;
    }

    public void setReasonForLeaving(String reasonForLeaving) {
        this.reasonForLeaving = reasonForLeaving;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferenceDesignation() {
        return referenceDesignation;
    }

    public void setReferenceDesignation(String referenceDesignation) {
        this.referenceDesignation = referenceDesignation;
    }

    public String getReferencePhone() {
        return referencePhone;
    }

    public void setReferencePhone(String referencePhone) {
        this.referencePhone = referencePhone;
    }

    @Override
    public String toString() {
        return "[" + employee + "] " + employer + ": " + designation;
    }
}
