package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.LoadSettlement;
import ph.txtdis.model.LoadSettlementDetail;
import ph.txtdis.model.Truck;

public interface LoadSettlementService extends Serviced<LoadSettlement, Integer> {

    List<LoadSettlementDetail> getDetail(Truck truck, LocalDate date);

    LoadSettlement get(Truck truck, LocalDate date);
}