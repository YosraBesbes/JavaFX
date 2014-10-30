package ph.txtdis.dto;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import ph.txtdis.model.RemittanceSettlement;
import ph.txtdis.model.RemittanceSettlementDetail;
import ph.txtdis.model.Truck;

public interface RemittanceSettlementDTO extends SettlementDTO<RemittanceSettlement> {

    ObservableList<RemittanceSettlementDetail> getSettlementDetail(Truck truck, LocalDate date);
}
