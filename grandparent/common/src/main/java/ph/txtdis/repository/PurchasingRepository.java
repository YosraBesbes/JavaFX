package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;

public interface PurchasingRepository extends CrudRepository<Purchasing, Integer> {

    @Query("select min(p.id) from Purchasing p")
    int getMinId();

    @Query("select max(p.id) from Purchasing p")
    int getMaxId();
    
    @Query("select p.details from Purchasing p where p.id = ?1")
    List<PurchasingDetail> getDetails(int id);
}
