package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Location;
import ph.txtdis.type.LocationType;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    List<Location> findByType(LocationType type);
}
