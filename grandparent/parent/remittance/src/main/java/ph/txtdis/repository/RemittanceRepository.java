package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Remittance;
import ph.txtdis.model.RemittanceDetail;

public interface RemittanceRepository extends CrudRepository<Remittance, Integer> {

    @Query("select min(r.id) from Remittance r")
    int getMinId();

    @Query("select max(r.id) from Remittance r")
    int getMaxId();
    
    @Query("select r.details from Remittance r where r.id = ?1")
    List<RemittanceDetail> getDetails(int id);
}
