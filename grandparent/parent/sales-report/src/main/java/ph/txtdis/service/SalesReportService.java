package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.ProductivityPerFamilyByRoute;
import ph.txtdis.model.VolumeByRoute;
import ph.txtdis.model.VolumePerChannelByRoute;
import ph.txtdis.model.VolumePerTownByRoute;
import ph.txtdis.model.VolumeSummary;

public interface SalesReportService {

    LocalDate getOldestMonth();

    LocalDate getLatestMonth();

    List<ProductivityPerFamilyByRoute> getProductivity(LocalDate date, LocalDate endDate);

    List<VolumeByRoute> getSummary(LocalDate startDate, LocalDate endDate);

    List<VolumePerChannelByRoute> getPerChannel(LocalDate startDate, LocalDate endDate);

    List<VolumePerTownByRoute> getPerTown(LocalDate startDate, LocalDate endDate);

    List<VolumeSummary> getPerItem(LocalDate startDate, LocalDate endDate);

    List<String> getRouteNames();
}