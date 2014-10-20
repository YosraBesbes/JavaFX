package ph.txtdis.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.ProductivityPerFamilyByRoute;
import ph.txtdis.model.VolumeByRoute;
import ph.txtdis.model.VolumePerChannelByRoute;
import ph.txtdis.model.VolumePerTownByRoute;
import ph.txtdis.model.VolumeSummary;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.SalesReportService;
import ph.txtdis.type.UomType;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

@Component
public class SalesReportDTOImpl implements SalesReportDTO {

    @Autowired
    private SalesReportService service;

    @Autowired
    private ItemService itemService;

    private LocalDate date, startDate, endDate;

    @Override
    public void back() {
        if (getStartDate().isEqual(getOldest()))
            setDateIfUnequal(getLatest());
        else
            setDate(getPrevious());
    }

    @Override
    public void next() {
        if (getStartDate().isEqual(getLatest()))
            setDateIfUnequal(getOldest());
        else
            setDate(getNext());
    }

    private LocalDate getOldest() {
        return service.getOldestMonth();
    }

    private LocalDate getLatest() {
        return service.getLatestMonth();
    }

    private LocalDate getPrevious() {
        return getStartDate().minusMonths(1L);
    }

    private LocalDate getNext() {
        return getStartDate().plusMonths(1L);
    }

    private void setDateIfUnequal(LocalDate date) {
        if (!getStartDate().isEqual(date))
            setDate(date);
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate == null ? Util.startOfMonth(getDate()) : startDate;
    }

    @Override
    public void setStartDate(LocalDate date) {
        startDate = date;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate == null ? Util.endOfMonth(getDate()) : endDate;
    }

    @Override
    public void setEndDate(LocalDate date) {
        endDate = date;
    }

    @Override
    public ObservableList<ProductivityPerFamilyByRoute> getProductivity(LocalDate startDate, LocalDate endDate) {
        return FXCollections.observableList(service.getProductivity(startDate, endDate));
    }

    @Override
    public ObservableList<VolumeByRoute> getSummary(LocalDate startDate, LocalDate endDate) {
        return FXCollections.observableList(service.getSummary(startDate, endDate));
    }

    private VolumeSummary createPerCaseVolumeSummary(VolumeSummary summary) {
        return new VolumeSummary(summary.getItem(), summary.getQty().divide(getQtyPerCS(summary), 2,
                RoundingMode.HALF_EVEN));
    }

    private BigDecimal getQtyPerCS(VolumeSummary summary) {
        return itemService.getQtyPerUomMap(summary.getItemId()).get(UomType.CS);
    }

    @Override
    public ObservableList<VolumePerChannelByRoute> getPerChannel(LocalDate startDate, LocalDate endDate) {
        return FXCollections.observableList(service.getPerChannel(startDate, endDate));
    }

    @Override
    public ObservableList<VolumePerTownByRoute> getPerTown(LocalDate startDate, LocalDate endDate) {
        return FXCollections.observableList(service.getPerTown(startDate, endDate));
    }

    @Override
    public ObservableList<VolumeSummary> getPerItem(LocalDate startDate, LocalDate endDate) {
        return FXCollections.observableList(createSoldItemList(startDate, endDate, new ArrayList<VolumeSummary>()));
    }

    private List<VolumeSummary> createSoldItemList(LocalDate startDate, LocalDate endDate, List<VolumeSummary> list) {
        for (VolumeSummary summary : service.getPerItem(startDate, endDate))
            if (!DIS.isZero(summary.getQty()))
                list.add(createPerCaseVolumeSummary(summary));
        return list;
    }

    @Override
    public List<String> getRouteNames() {
        return service.getRouteNames();
    }
}
