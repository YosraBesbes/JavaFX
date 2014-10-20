package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.ItemTree;

public interface ItemTreeRepository extends CrudRepository<ItemTree, Integer> {
}