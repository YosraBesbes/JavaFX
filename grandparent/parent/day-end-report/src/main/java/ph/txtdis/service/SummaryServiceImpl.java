package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.DailySummary;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.Truck;
import ph.txtdis.model.VolumeSummary;
import ph.txtdis.repository.InvoicingRepository;
import ph.txtdis.repository.RemittanceRepository;
import ph.txtdis.repository.SummaryRepository;
import ph.txtdis.util.Util;

@Service
@Transactional()
public class SummaryServiceImpl extends AbstractService<DailySummary, LocalDate> implements SummaryService {

    @Autowired
    private SummaryRepository repository;

    @Autowired
    private InvoicingRepository invoicingRepository;

    @Autowired
    private RemittanceRepository remittanceRepository;

    protected SummaryServiceImpl() {
    }

    @Override
    public LocalDate getMinId() {
        return repository.getOldestDate();
    }

    @Override
    public LocalDate getMaxId() {
        return repository.getLatestDate();
    }

    @Override
    public List<VolumeSummary> getVolumeSummary(LocalDate date) {
        return repository.getVolumeSummary(date);
    }

    @Override
    public List<Invoicing> getInvoices(LocalDate date) {
        return invoicingRepository.findByOrderDateOrderByIdAsc(date);
    }

    @Override
    public List<Remittance> getRemittances(LocalDate date) {
        return remittanceRepository.findByTimeStampBetweenOrderByIdAsc(Util.startOfDay(date), Util.endOfDay(date));
    }

    @Override
    public List<VolumeSummary> getVolumeSummary(LocalDate date, Truck truck) {
        return repository.getVolumeSummary(date, truck);
    }

    @Override
    public List<Invoicing> getInvoices(LocalDate date, Truck truck) {
        return invoicingRepository.getInvoices(date, truck);
    }

    @Override
    public List<Remittance> getRemittances(LocalDate date, Truck truck) {
        return remittanceRepository.getRemittances(date, truck);
    }
}
