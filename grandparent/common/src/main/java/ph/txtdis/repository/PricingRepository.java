package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Item;
import ph.txtdis.model.Pricing;
import ph.txtdis.type.PricingType;

public interface PricingRepository extends CrudRepository<Pricing, Integer> {

    List<Pricing> findByItemAndTypeAndStartDateBeforeOrderByStartDateDesc(Item item, PricingType type, LocalDate date,
            Pageable pageable);
}
