package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.RemittanceSettlement;
import ph.txtdis.model.RemittanceSettlementDetail;
import ph.txtdis.model.Truck;

public interface RemittanceSettlementService extends Serviced<RemittanceSettlement, Integer> {

    List<RemittanceSettlementDetail> getDetail(Truck truck, LocalDate date);

    RemittanceSettlement get(Truck truck, LocalDate date);
}