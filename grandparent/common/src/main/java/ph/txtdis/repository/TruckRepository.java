package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Truck;

public interface TruckRepository extends CrudRepository<Truck, Integer> {

    Truck findOneByName(String name);
}
