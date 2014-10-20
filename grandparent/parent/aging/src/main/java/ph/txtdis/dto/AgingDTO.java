package ph.txtdis.dto;

import java.math.BigDecimal;

import javafx.collections.ObservableList;
import ph.txtdis.model.Aging;

public interface AgingDTO {

    ObservableList<Aging> getAgingList();

    BigDecimal[] getTotals();
}
