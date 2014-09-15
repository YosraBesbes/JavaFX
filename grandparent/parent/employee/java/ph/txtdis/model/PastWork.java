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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "start_date", "employer", "designation" }), name = "past_work")
public class PastWork extends AbstractAudited {

    private static final long serialVersionUID = 4972100444095311026L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(nullable = false)
    private String employer;

    @Column(nullable = false)
    private String designation;

    @Column(name = "last_pay", nullable = false)
    private BigDecimal lastPay;

    @Column(name = "reason_for_leaving", nullable = false)
    private String reasonForLeaving;

    @Column(name = "reference_name", nullable = false)
    private String referenceName;

    @Column(name = "reference_designation", nullable = false)
    private String referenceDesignation;

    @Column(name = "reference_phone", nullable = false)
    private String referencePhone;

    protected PastWork() {
    }

    public PastWork(Employee employee, Date startDate, Date endDate, String employer, String designation,
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
