package ph.txtdis.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.ProductivityPerFamilyByRoute;
import ph.txtdis.model.VolumeByRoute;
import ph.txtdis.model.VolumePerChannelByRoute;
import ph.txtdis.model.VolumePerTownByRoute;
import ph.txtdis.model.VolumeSummary;
import ph.txtdis.repository.InvoicingRepository;
import ph.txtdis.repository.RouteRepository;
import ph.txtdis.util.Util;

@Service
@Transactional()
public class SalesReportServiceImpl implements SalesReportService {

    @Autowired
    private InvoicingRepository repository;

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public LocalDate getOldestMonth() {
        return Util.startOfMonth(repository.getOldestMonth());
    }

    @Override
    public LocalDate getLatestMonth() {
        return Util.startOfMonth(repository.getLatestMonth());
    }

    @Override
    public List<ProductivityPerFamilyByRoute> getProductivity(LocalDate startDate, LocalDate endDate) {
        return repository.getProductivity(startDate, endDate);
    }

    @Override
    public List<VolumeByRoute> getSummary(LocalDate startDate, LocalDate endDate) {
        return repository.getSummary(startDate, endDate);
    }

    @Override
    public List<VolumePerChannelByRoute> getPerChannel(LocalDate startDate, LocalDate endDate) {
        return repository.getPerChannel(startDate, endDate);
    }

    @Override
    public List<VolumePerTownByRoute> getPerTown(LocalDate startDate, LocalDate endDate) {
        return repository.getPerTown(startDate, endDate);
    }

    @Override
    public List<VolumeSummary> getPerItem(LocalDate startDate, LocalDate endDate) {
        return repository.getPerItem(startDate, endDate);
    }

    @Override
    public List<String> getRouteNames() {
        List<String> routeNames = new ArrayList<>();
        routeRepository.findAll().forEach(route -> routeNames.add(route.toString()));
        return routeNames;
    }
}
