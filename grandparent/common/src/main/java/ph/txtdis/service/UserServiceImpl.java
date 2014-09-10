package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.SystemUser;
import ph.txtdis.repository.UserRepository;

@Service
@Transactional()
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	protected UserServiceImpl() {
	}

	@Override
	public boolean exists(String username) {
		return repository.exists(username);
	}
	
	@Override
    public SystemUser get(String username) {
        return repository.findOne(username);
    }
    
    @Override
    public SystemUser get(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }
    
	@Override
	public SystemUser save(SystemUser user) {
		return repository.save(user);
	}
	
	@Override
	public void delete(SystemUser user) {
		repository.delete(user);
	}

    @Override
    public List<SystemUser> list() {
        return repository.list();
    }	
}
