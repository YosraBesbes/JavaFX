package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ph.txtdis.type.ContactInfoType;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "employee_id", "type", "detail" }) }, name = "employee_contact_info")
public class EmployeeContactInfo extends AbstractAudited {

    private static final long serialVersionUID = -3583165118929909989L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    private ContactInfoType type;

    @Column(nullable = false)
    private String detail;

    protected EmployeeContactInfo() {
    }

    public EmployeeContactInfo(Employee employee, ContactInfoType type, String detail) {
        this.employee = employee;
        this.type = type;
        this.detail = detail;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ContactInfoType getType() {
        return type;
    }

    public void setType(ContactInfoType type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "[" + employee + "] " + type + ": " + detail;
    }
}
