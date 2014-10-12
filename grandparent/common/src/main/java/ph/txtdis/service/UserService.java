package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.SystemUser;
import ph.txtdis.type.UserType;

public interface UserService {

    boolean exists(String username);

    SystemUser get(String username);

    SystemUser get(String username, String password);

    SystemUser getByEmail(String email);

    SystemUser save(SystemUser user);

    void delete(SystemUser user);

    List<SystemUser> listAll();

    List<SystemUser> list(UserType type);
}
