package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.ProgressDialog;

public class StockTakeSummaryButton extends FontButton<Object> {

    public <C> StockTakeSummaryButton(Stage stage) {
        super("\ue829", "Stock Take\nSummary", 44);
        button.setOnAction(event -> {
            new ProgressDialog(stage) {
                @Override
                protected void begin() {
                }

                @Override
                protected void next() {
                }
            };
        });
    }
}
