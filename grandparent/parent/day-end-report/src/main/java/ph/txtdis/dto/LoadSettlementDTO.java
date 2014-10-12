package ph.txtdis.dto;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import ph.txtdis.model.LoadSettlement;
import ph.txtdis.model.LoadSettlementFilteredDetail;
import ph.txtdis.model.Truck;

public interface LoadSettlementDTO extends SettlementDTO<LoadSettlement> {

    ObservableList<LoadSettlementFilteredDetail> getSettlementFilteredDetail(Truck truck, LocalDate date);
}
