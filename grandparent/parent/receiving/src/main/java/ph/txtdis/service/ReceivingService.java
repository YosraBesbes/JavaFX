package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.Receiving;
import ph.txtdis.model.ReceivingDetail;
import ph.txtdis.model.ReceivingSummary;

public interface ReceivingService extends OrderService<Receiving, ReceivingDetail> {

    List<ReceivingSummary> getSummary(LocalDate startDate, LocalDate endDate);

    LocalDate getDateFromPurchaseOrder(int id);
}