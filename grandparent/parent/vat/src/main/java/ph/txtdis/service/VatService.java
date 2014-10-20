package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.Vat;

public interface VatService {

    LocalDate getOldestMonth();

    LocalDate getLatestMonth();

    List<Vat> getVatList(LocalDate startDate, LocalDate endDate);
}