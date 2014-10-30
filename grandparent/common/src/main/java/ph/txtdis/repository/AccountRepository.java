package ph.txtdis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("select min(a.id) from Account a")
    int getMinId();

    @Query("select max(a.id) from Account a")
    int getMaxId();
}
