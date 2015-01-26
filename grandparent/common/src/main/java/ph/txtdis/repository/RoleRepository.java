package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ph.txtdis.model.Authorities;
import ph.txtdis.model.Users;

public interface RoleRepository extends CrudRepository<Authorities, Integer> {

    @Query("select a.authority from Authorities a where a.username = :username")
    List<String> getRoles(@Param("username") Users username);
}
