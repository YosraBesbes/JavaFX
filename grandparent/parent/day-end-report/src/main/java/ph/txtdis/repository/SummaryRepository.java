package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.DailySummary;
import ph.txtdis.model.Truck;
import ph.txtdis.model.VolumeSummary;

public interface SummaryRepository extends CrudRepository<DailySummary, LocalDate> {

    @Query("select min(s.id) from DailySummary s")
    LocalDate getOldestDate();

    @Query("select max(s.id) from DailySummary s")
    LocalDate getLatestDate();

    @Query("select new ph.txtdis.model.VolumeSummary(s.item, sum(s.qty)) from InvoicedVolumeView s "
            + "where s.orderDate = ?1 group by s.item ")
    List<VolumeSummary> getVolumeSummary(LocalDate date);

    @Query("select new ph.txtdis.model.VolumeSummary(s.item, sum(s.qty)) from InvoicedVolumeByTruckView s "
            + "where s.orderDate = ?1 and s.truck = ?2 group by s.item ")
    List<VolumeSummary> getVolumeSummary(LocalDate date, Truck truck);
}
