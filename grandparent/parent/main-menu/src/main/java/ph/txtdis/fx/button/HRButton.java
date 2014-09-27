package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.EmployeeAppImpl;

public class HRButton extends FontButton<Object> {

    public <C> HRButton(Stage stage) {
        super("\ue811", "Human Resources", 44);
        button.setOnAction(event -> new EmployeeAppImpl().start());
    }
}
