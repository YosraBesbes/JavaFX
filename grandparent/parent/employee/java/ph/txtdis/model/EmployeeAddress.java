package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ph.txtdis.type.AddressType;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "employee_id", "type", "location" }) }, name = "employee_address")
public class EmployeeAddress extends AbstractAudited {

    private static final long serialVersionUID = -305821699879355751L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    private AddressType type;

    @Column(length = 64, nullable = false)
    private String location;

    protected EmployeeAddress() {
    }

    public EmployeeAddress(Employee employee, AddressType type, String location) {
        this.employee = employee;
        this.type = type;
        this.location = location;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "[" + employee + "] " + type + ": " + location;
    }
}
