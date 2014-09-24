package ph.txtdis.dto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

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
import ph.txtdis.service.EmployeeService;
import ph.txtdis.type.CivilStatus;
import ph.txtdis.type.FamilyType;
import ph.txtdis.util.Util;

@Component
public class EmployeeDTOImpl extends AbstractSearchedSpunDTO<Employee, String, EmployeeService> implements EmployeeDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Employee();
    }

    @Override
    public InputStream getImageStream() {
        byte[] photoBytes = entity.getPhoto();
        if (photoBytes == null)
            return this.getClass().getResourceAsStream("/image/photo.jpg");
        else
            return new ByteArrayInputStream(photoBytes);
    }

    @Override
    public void setImageStream(InputStream photoStream) {
        if (photoStream != null) {
            try {
                entity.setPhoto(IOUtils.toByteArray(photoStream));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getSurname() {
        return entity.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        entity.setSurname(surname);
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public void setName(String name) {
        entity.setName(name);
    }

    @Override
    public String getMiddleInitial() {
        return entity.getMiddleInitial();
    }

    @Override
    public void setMiddleInitial(String middleInitial) {
        entity.setMiddleInitial(middleInitial);
    }

    @Override
    public ObservableList<EmployeeAddress> getAddresses() {
        return FXCollections.observableList(service.getAddresses(id));
    }

    @Override
    public void setAddresses(List<EmployeeAddress> addresses) {
        entity.setAddresses(addresses);
    }

    @Override
    public ObservableList<EmployeeContactInfo> getContactInfo() {
        return FXCollections.observableList(service.getContactInfo(id));
    }

    @Override
    public void setContactInfo(List<EmployeeContactInfo> contactInfo) {
        entity.setContactInfo(contactInfo);
    }

    @Override
    public LocalDate getBirthdate() {
        Date birthdate = entity.getBirthdate();
        return birthdate == null ? null : birthdate.toLocalDate();
    }

    @Override
    public void setBirthdate(LocalDate birthdate) {
        entity.setBirthdate(Util.localToSqlDate(birthdate));
    }

    @Override
    public String getBirthplace() {
        return entity.getBirthplace();
    }

    @Override
    public void setBirthplace(String birthplace) {
        entity.setBirthplace(birthplace);
    }

    @Override
    public CivilStatus getCivilStatus() {
        return entity.getCivilStatus();
    }

    @Override
    public void setCivilStatus(CivilStatus civilStatus) {
        entity.setCivilStatus(civilStatus);
    }

    @Override
    public String getEmergencyContact() {
        return entity.getEmergencyContact();
    }

    @Override
    public void setEmergencyContact(String emergencyContact) {
        entity.setEmergencyContact(emergencyContact);
    }

    @Override
    public FamilyType getEmergencyRelation() {
        return entity.getEmergencyRelation();
    }

    @Override
    public void setEmergencyRelation(FamilyType emergencyRelation) {
        entity.setEmergencyRelation(emergencyRelation);
    }

    @Override
    public String getEmergencyPhone() {
        return entity.getEmergencyPhone();
    }

    @Override
    public void setEmergencyPhone(String emergencyPhone) {
        entity.setEmergencyPhone(emergencyPhone);
    }

    @Override
    public ObservableList<GovtId> getGovtIds() {
        return FXCollections.observableList(service.getGovtIds(id));
    }

    @Override
    public void setGovtIds(List<GovtId> govtIds) {
        entity.setGovtIds(govtIds);
    }

    @Override
    public ObservableList<Family> getRelatives() {
        return FXCollections.observableList(service.getRelatives(id));
    }

    @Override
    public void setRelatives(List<Family> relatives) {
        entity.setRelatives(relatives);
    }

    @Override
    public ObservableList<Education> getStudies() {
        return FXCollections.observableList(service.getStudies(id));
    }

    @Override
    public void setStudies(List<Education> studies) {
        entity.setStudies(studies);
    }

    @Override
    public ObservableList<PastWork> getPastJobs() {
        return FXCollections.observableList(service.getPastJobs(id));
    }

    @Override
    public void setPastJobs(List<PastWork> pastJobs) {
        entity.setPastJobs(pastJobs);
    }

    @Override
    public ObservableList<Assignment> getAssignments() {
        return FXCollections.observableList(service.getAssignments(id));
    }

    @Override
    public void setAssignments(List<Assignment> assignments) {
        entity.setAssignments(assignments);
    }

    @Override
    public ObservableList<Compensation> getDailyRates() {
        return FXCollections.observableList(service.getDailyRates(id));
    }

    @Override
    public void setDailyRates(List<Compensation> dailyRates) {
        entity.setDailyRates(dailyRates);
    }

    @Override
    public ObservableList<Leave> getLeaves() {
        return FXCollections.observableList(service.getLeaves(id));
    }

    @Override
    public void setLeaves(List<Leave> leaves) {
        entity.setLeaves(leaves);
    }

    @Override
    public ObservableList<Loan> getLoans() {
        return FXCollections.observableList(service.getLoans(id));
    }

    @Override
    public void setLoans(List<Loan> loans) {
        entity.setLoans(loans);
    }

    @Override
    public ObservableList<Discipline> getDisciplinaryActions() {
        return FXCollections.observableList(service.getDisciplinaryActions(id));
    }

    @Override
    public void setDisciplinaryActions(List<Discipline> disciplinaryActions) {
        entity.setDisciplinaryActions(disciplinaryActions);
    }
}
