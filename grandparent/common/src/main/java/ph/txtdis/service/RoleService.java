package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.Users;

public interface RoleService {

    List<String> getRoles(Users users);
}
