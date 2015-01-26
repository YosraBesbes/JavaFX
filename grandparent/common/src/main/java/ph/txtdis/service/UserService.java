package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.Users;
import ph.txtdis.type.UserType;

public interface UserService {

    boolean exists(String username);

    Users get(String username);

    Users get(String username, String password);

    Users getByEmail(String email);

    Users save(Users user);

    void delete(Users user);

    List<Users> listAll();

    List<Users> list(UserType type);
}
