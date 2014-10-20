package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Vat;
import ph.txtdis.repository.InvoicingRepository;
import ph.txtdis.util.Util;

@Service
@Transactional()
public class VatServiceImpl implements VatService {

    @Autowired
    private InvoicingRepository repository;

    @Override
    public LocalDate getOldestMonth() {
        return Util.startOfMonth(repository.getOldestMonth());
    }

    @Override
    public LocalDate getLatestMonth() {
        return Util.startOfMonth(repository.getLatestMonth());
    }

    @Override
    public List<Vat> getVatList(LocalDate startDate, LocalDate endDate) {
        return repository.getVatList(startDate, endDate);
    }
}
