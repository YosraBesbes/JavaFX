package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.BookingAppImpl;

public class BookingButton extends FontButton<Object> {

    public <C> BookingButton(Stage stage) {
        super("\ue805", "Sales Order", 44);
        button.setOnAction(event -> new BookingAppImpl().start());
    }
}
