package ph.txtdis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Style;
import ph.txtdis.model.SystemUser;

public interface StyleRepository extends CrudRepository<Style, Integer> {

	@Query("select s from Style s where s.systemUser = ?1")
	Style get(SystemUser user);
}
