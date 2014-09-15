package ph.txtdis.service;

import java.util.List;

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

public interface EmployeeService extends SearchedSpunServiced<Employee, String> {

    List<EmployeeAddress> getAddresses(int id);

    List<EmployeeContactInfo> getContactInfo(int id);

    List<GovtId> getGovtIds(int id);

    List<Family> getRelatives(int id);

    List<Education> getStudies(int id);

    List<PastWork> getPastJobs(int id);

    List<Assignment> getAssignments(int id);

    List<Compensation> getDailyRates(int id);

    List<Leave> getLeaves(int id);

    List<Loan> getLoans(int id);

    List<Discipline> getDisciplinaryActions(int id);
}