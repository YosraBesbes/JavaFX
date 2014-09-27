package ph.txtdis.dto;

import javafx.collections.ObservableList;
import ph.txtdis.model.Inventory;

public interface InventoryDTO {

    String getStartDateText();

    String getEndDateText();

    ObservableList<Inventory> getInventoryList();
}
