package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Location;
import ph.txtdis.model.LocationTree;

public interface LocationTreeRepository extends CrudRepository<LocationTree, Integer> {

    @Query("select t.location from LocationTree t where t.parent = ?1")
    List<Location> findChildren(Location parent);
}
