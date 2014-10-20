package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.DayEndAppImpl;

public class DayEndReportButton extends FontButton<Object> {

    public <C> DayEndReportButton(Stage stage) {
        super("\ue807", "Day-End Report", 44);
        button.setOnAction(event -> new DayEndAppImpl().start());
    }
}
