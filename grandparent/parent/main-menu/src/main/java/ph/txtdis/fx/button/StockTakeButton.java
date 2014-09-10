package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.StockTakeAppImpl;

public class StockTakeButton extends FontButton<Object> {

    public <C> StockTakeButton(Stage stage) {
        super("\ue828", "Stock Take", 44);
        button.setOnAction(event -> new StockTakeAppImpl().start());
    }
}
