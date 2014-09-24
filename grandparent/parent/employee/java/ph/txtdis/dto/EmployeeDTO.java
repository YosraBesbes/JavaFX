package ph.txtdis.dto;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import ph.txtdis.fx.tab.ImageStreamed;
import ph.txtdis.model.Assignment;
import ph.txtdis.model.Compensation;
import ph.txtdis.model.Discipline;
import ph.txtdis.model.Education;
import ph.txtdis.model.Employee;
import ph.txtdis.model.EmployeeAddress;
import ph.txtdis.model.EmployeeContactInfo;
import ph.txtdis.model.Family;
import ph.txtdis.model.GovtId;
import ph.txtdis.model.Leave;
import ph.txtdis.model.Loan;
import ph.txtdis.model.PastWork;
import ph.txtdis.type.CivilStatus;
import ph.txtdis.type.FamilyType;

public interface EmployeeDTO extends SearchedDTO<Employee, String>, SpunDTO, DTO<Employee>, ImageStreamed {

    String getSurname();

    void setSurname(String surname);

    String getName();

    void setName(String name);

    String getMiddleInitial();

    void setMiddleInitial(String middleInitial);

    ObservableList<EmployeeAddress> getAddresses();

    void setAddresses(List<EmployeeAddress> addresses);

    LocalDate getBirthdate();

    void setBirthdate(LocalDate birthdate);

    String getBirthplace();

    void setBirthplace(String birthPlace);

    CivilStatus getCivilStatus();

    void setCivilStatus(CivilStatus civilStatus);

    String getEmergencyContact();

    void setEmergencyContact(String emergencyContact);

    FamilyType getEmergencyRelation();

    void setEmergencyRelation(FamilyType emergencyRelation);

    String getEmergencyPhone();

    void setEmergencyPhone(String emergencyPhone);

    ObservableList<EmployeeContactInfo> getContactInfo();

    void setContactInfo(List<EmployeeContactInfo> contactInfo);

    ObservableList<GovtId> getGovtIds();

    void setGovtIds(List<GovtId> govtIds);

    ObservableList<Family> getRelatives();

    void setRelatives(List<Family> relatives);

    ObservableList<Education> getStudies();

    void setStudies(List<Education> studies);

    ObservableList<PastWork> getPastJobs();

    void setPastJobs(List<PastWork> pastJobs);

    ObservableList<Assignment> getAssignments();

    void setAssignments(List<Assignment> assignments);

    ObservableList<Compensation> getDailyRates();

    void setDailyRates(List<Compensation> dailyRates);

    ObservableList<Leave> getLeaves();

    void setLeaves(List<Leave> leaves);

    ObservableList<Loan> getLoans();

    void setLoans(List<Loan> loans);

    ObservableList<Discipline> getDisciplinaryActions();

    void setDisciplinaryActions(List<Discipline> disciplinaryActions);
}