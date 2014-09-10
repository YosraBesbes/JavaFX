package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Item;
import ph.txtdis.model.VolumeDiscount;

public interface VolumeDiscountRepository extends CrudRepository<VolumeDiscount, Integer> {

    List<VolumeDiscount> findByItemAndStartDateBeforeOrderByStartDateDesc(Item item, LocalDate date, Pageable pageable);
}
