package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

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

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("select min(e.id) from Employee e")
    int getMinId();

    @Query("select max(e.id) from Employee e")
    int getMaxId();

    @Query("select e.addresses from Employee e where e.id = ?1")
    List<EmployeeAddress> getAddresses(int id);

    @Query("select e.contactInfo from Employee e where e.id = ?1")
    List<EmployeeContactInfo> getContactInfo(int id);

    @Query("select e.govtIds from Employee e where e.id = ?1")
    List<GovtId> getGovtIds(int id);

    @Query("select e.relatives from Employee e where e.id = ?1")
    List<Family> getRelatives(int id);

    @Query("select e.studies from Employee e where e.id = ?1")
    List<Education> getStudies(int id);

    @Query("select e.pastJobs from Employee e where e.id = ?1")
    List<PastWork> getPastJobs(int id);

    @Query("select e.assignments from Employee e where e.id = ?1")
    List<Assignment> getAssignments(int id);

    @Query("select e.dailyRates from Employee e where e.id = ?1")
    List<Compensation> getDailyRates(int id);

    @Query("select e.leaves from Employee e where e.id = ?1")
    List<Leave> getLeaves(int id);

    @Query("select e.loans from Employee e where e.id = ?1")
    List<Loan> getLoans(int id);

    @Query("select e.disciplinaryActions from Employee e where e.id = ?1")
    List<Discipline> getDisciplinaryActions(int id);

    List<Employee> findBySurnameContainingOrNameContaining(String surname, String name);
}
