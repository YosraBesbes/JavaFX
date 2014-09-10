package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.InvoiceBooklet;

public interface InvoiceBookletRepository extends CrudRepository<InvoiceBooklet, Integer> {
    
    InvoiceBooklet findByStartIdLessThanEqualAndEndIdGreaterThanEqual(int startId, int endId);
}
