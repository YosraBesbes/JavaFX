package ph.txtdis.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ph.txtdis.type.FamilyType;

@Entity
public class Family extends AbstractAudited {

    private static final long serialVersionUID = -5822699684522028625L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    private FamilyType type;

    @Column(length = 16, nullable = false)
    private String surname;

    @Column(length = 24, nullable = false)
    private String name;

    @Column(length = 1, nullable = false)
    private String middleInitial;

    @Column(nullable = false)
    private Date birthdate;

    @Column(length = 16, nullable = false)
    private String institution;

    @Column(length = 24, nullable = false)
    private String designation;

    protected Family() {
    }

    public Family(Employee employee, FamilyType type, String surname, String name, String middleInitial,
            Date birthdate, String institution, String designation) {
        this.employee = employee;
        this.type = type;
        this.surname = surname;
        this.name = name;
        this.middleInitial = middleInitial;
        this.birthdate = birthdate;
        this.institution = institution;
        this.designation = designation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public FamilyType getType() {
        return type;
    }

    public void setType(FamilyType type) {
        this.type = type;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return surname + ", " + name + " " + middleInitial + ".";
    }
}
