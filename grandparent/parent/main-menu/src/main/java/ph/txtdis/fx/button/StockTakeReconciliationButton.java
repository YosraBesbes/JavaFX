package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.StockTakeReconciliationAppImpl;

public class StockTakeReconciliationButton extends FontButton<Object> {

    public <C> StockTakeReconciliationButton(Stage stage) {
        super("\ue82a", "Stock Take\nReconciliation", 44);
        button.setOnAction(event -> new StockTakeReconciliationAppImpl().start());
    }
}
