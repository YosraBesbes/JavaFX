package ph.txtdis.fx.button;

import ph.txtdis.fx.dialog.LoginDialog;
import ph.txtdis.model.Users;
import ph.txtdis.type.LoginType;

public class ServerButton extends TextButton<Users> {

    public ServerButton(LoginDialog app) {
        super("Change Server");
        button.setOnAction(event -> app.validate(LoginType.SERVER));
    }
}
