package ph.txtdis.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Item;
import ph.txtdis.model.Pricing;
import ph.txtdis.type.PricingType;

public interface PricingRepository extends CrudRepository<Pricing, Integer> {

    Pricing findFirstByItemAndTypeAndStartDateLessThanEqualOrderByStartDateDesc(Item item, PricingType type,
            LocalDate date);
}
