package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.DailySummary;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.Truck;
import ph.txtdis.model.VolumeSummary;

public interface SummaryService extends SpunService<DailySummary, LocalDate> {

    List<VolumeSummary> getVolumeSummary(LocalDate date);

    List<Invoicing> getInvoices(LocalDate date);

    List<Remittance> getRemittances(LocalDate date);

    List<VolumeSummary> getVolumeSummary(LocalDate date, Truck truck);

    List<Invoicing> getInvoices(LocalDate date, Truck truck);

    List<Remittance> getRemittances(LocalDate date, Truck truck);
}