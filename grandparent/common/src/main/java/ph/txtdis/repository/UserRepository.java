package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ph.txtdis.model.Users;
import ph.txtdis.type.UserType;

@Repository
public interface UserRepository extends CrudRepository<Users, String> {

    Users findByUsernameAndPassword(String username, String password);

    Users findByEmail(String email);

    List<Users> findByType(UserType type);

    @Query("select u from Users u where u.enabled = true order by u.username")
    List<Users> list();
}
