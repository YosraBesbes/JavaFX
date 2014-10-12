package ph.txtdis.dto;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import ph.txtdis.model.RemittanceSettlement;
import ph.txtdis.model.RemittanceSettlementFilteredDetail;
import ph.txtdis.model.Truck;

public interface RemittanceSettlementDTO extends SettlementDTO<RemittanceSettlement> {

    ObservableList<RemittanceSettlementFilteredDetail> getSettlementFilteredDetail(Truck truck, LocalDate date);
}
