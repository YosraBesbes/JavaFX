package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.SystemUser;

public interface UserService {

	boolean exists(String username);

    SystemUser get(String username);

    SystemUser get(String username, String password);

	SystemUser save(SystemUser user);

	void delete(SystemUser user);
	
	List<SystemUser> list();
}
