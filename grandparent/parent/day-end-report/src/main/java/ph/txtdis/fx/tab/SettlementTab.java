package ph.txtdis.fx.tab;

import javafx.beans.binding.BooleanBinding;

public interface SettlementTab {

    BooleanBinding isAllReconciled();

    BooleanBinding isAnyOpen();

    boolean isDataReadyToBeSaved();
}
