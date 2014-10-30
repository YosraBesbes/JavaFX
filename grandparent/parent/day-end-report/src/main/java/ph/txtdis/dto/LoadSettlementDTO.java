package ph.txtdis.dto;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import ph.txtdis.model.LoadSettlement;
import ph.txtdis.model.LoadSettlementDetail;
import ph.txtdis.model.Truck;

public interface LoadSettlementDTO extends SettlementDTO<LoadSettlement> {

    ObservableList<LoadSettlementDetail> getSettlementDetail(Truck truck, LocalDate date);
}
