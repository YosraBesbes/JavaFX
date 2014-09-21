package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.SystemUser;
import ph.txtdis.type.UserType;

public interface UserRepository extends CrudRepository<SystemUser, String> {

    SystemUser findByUsernameAndPassword(String username, String password);

    SystemUser findByEmail(String email);

    List<SystemUser> findByType(UserType type);

    @Query("select u from SystemUser u where u.enabled = true order by u.username")
    List<SystemUser> list();
}
