package ph.txtdis.dto;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import ph.txtdis.model.ProductivityPerFamilyByRoute;
import ph.txtdis.model.VolumeByRoute;
import ph.txtdis.model.VolumePerChannelByRoute;
import ph.txtdis.model.VolumePerTownByRoute;
import ph.txtdis.model.VolumeSummary;

public interface SalesReportDTO extends DateRanged, Spun {

    LocalDate getDate();

    void setDate(LocalDate date);

    ObservableList<ProductivityPerFamilyByRoute> getProductivity(LocalDate date, LocalDate endDate);

    ObservableList<VolumeByRoute> getSummary(LocalDate startDate, LocalDate endDate);

    ObservableList<VolumePerChannelByRoute> getPerChannel(LocalDate startDate, LocalDate endDate);

    ObservableList<VolumePerTownByRoute> getPerTown(LocalDate startDate, LocalDate endDate);

    ObservableList<VolumeSummary> getPerItem(LocalDate startDate, LocalDate endDate);

    List<String> getRouteNames();
}
