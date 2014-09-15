package ph.txtdis.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import ph.txtdis.type.CivilStatus;
import ph.txtdis.type.FamilyType;

@Entity
public class Employee extends AbstractAudited {

    private static final long serialVersionUID = -4623243890410324949L;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(name = "middle_initial", length = 1)
    private String middleInitial;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] photo;

    private Date birthdate;
    private String birthplace;
    private CivilStatus civilStatus;
    private String emergencyContact;
    private FamilyType emergencyRelation;
    private String emergencyPhone;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeAddress> addresses;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeContactInfo> contactInfo;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GovtId> govtIds;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Family> relatives;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> studies;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PastWork> pastJobs;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compensation> dailyRates;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leave> leaves;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discipline> disciplinaryActions;

    public Employee() {
    }

    public Employee(String surname, String name) {
        this.surname = surname;
        this.name = name;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public CivilStatus getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(CivilStatus civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public FamilyType getEmergencyRelation() {
        return emergencyRelation;
    }

    public void setEmergencyRelation(FamilyType emergencyRelation) {
        this.emergencyRelation = emergencyRelation;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public List<EmployeeAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<EmployeeAddress> addresses) {
        this.addresses = addresses;
    }

    public List<EmployeeContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<EmployeeContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<GovtId> getGovtIds() {
        return govtIds;
    }

    public void setGovtIds(List<GovtId> govtIds) {
        this.govtIds = govtIds;
    }

    public List<Family> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Family> relatives) {
        this.relatives = relatives;
    }

    public List<Education> getStudies() {
        return studies;
    }

    public void setStudies(List<Education> studies) {
        this.studies = studies;
    }

    public List<PastWork> getPastJobs() {
        return pastJobs;
    }

    public void setPastJobs(List<PastWork> pastJobs) {
        this.pastJobs = pastJobs;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Compensation> getDailyRates() {
        return dailyRates;
    }

    public void setDailyRates(List<Compensation> dailyRates) {
        this.dailyRates = dailyRates;
    }

    public List<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leave> leaves) {
        this.leaves = leaves;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Discipline> getDisciplinaryActions() {
        return disciplinaryActions;
    }

    public void setDisciplinaryActions(List<Discipline> disciplinaryActions) {
        this.disciplinaryActions = disciplinaryActions;
    }

    @Override
    public String toString() {
        return surname + ", " + name;
    }
}
