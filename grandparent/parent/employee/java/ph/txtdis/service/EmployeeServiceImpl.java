package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import ph.txtdis.repository.EmployeeRepository;

@Service
@Transactional()
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    protected EmployeeServiceImpl() {
    }

    @Override
    public List<EmployeeAddress> getAddresses(int id) {
        return repository.getAddresses(id);
    }

    @Override
    public List<EmployeeContactInfo> getContactInfo(int id) {
        return repository.getContactInfo(id);
    }

    @Override
    public List<GovtId> getGovtIds(int id) {
        return repository.getGovtIds(id);
    }

    @Override
    public List<Family> getRelatives(int id) {
        return repository.getRelatives(id);
    }

    @Override
    public List<Education> getStudies(int id) {
        return repository.getStudies(id);
    }

    @Override
    public List<PastWork> getPastJobs(int id) {
        return repository.getPastJobs(id);
    }

    @Override
    public List<Assignment> getAssignments(int id) {
        return repository.getAssignments(id);
    }

    @Override
    public List<Compensation> getDailyRates(int id) {
        return repository.getDailyRates(id);
    }

    @Override
    public List<Leave> getLeaves(int id) {
        return repository.getLeaves(id);
    }

    @Override
    public List<Loan> getLoans(int id) {
        return repository.getLoans(id);
    }

    @Override
    public List<Discipline> getDisciplinaryActions(int id) {
        return repository.getDisciplinaryActions(id);
    }

    @Override
    public List<Employee> findAll(String name) {
        return repository.findBySurnameContainingOrNameContaining(name, name);
    }

    @Override
    public int getMinId() {
        return repository.getMinId();
    }

    @Override
    public int getMaxId() {
        return repository.getMaxId();
    }
}
