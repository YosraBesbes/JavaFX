package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.PickListPrinting;
import ph.txtdis.model.Picking;

public interface PickListPrintingRepository extends CrudRepository<PickListPrinting, Integer> {

    PickListPrinting findByPicking(Picking picking);
}
