package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.AddUserDialog;

public class UserButton extends FontButton<Object> {

    public <C> UserButton(Stage stage) {
        super("\ue82d", "System User", 44);
        button.setOnAction(event -> new AddUserDialog().showAndWait());
    }
}
