package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Warehouse;

public interface WarehouseRepository extends CrudRepository<Warehouse, Integer> {

    Warehouse findOneByName(String name);
}
