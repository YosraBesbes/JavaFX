package ph.txtdis.fx.button;

import javafx.stage.Stage;

public class DayEndReportButton extends FontButton<Object> {

    public <C> DayEndReportButton(Stage stage) {
        super("\ue80b", "Day-End Report", 44);
        // button.setOnAction(event -> new SummaryAppImpl().start());
    }
}
