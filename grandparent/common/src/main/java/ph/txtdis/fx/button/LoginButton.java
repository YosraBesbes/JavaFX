package ph.txtdis.fx.button;

import ph.txtdis.fx.dialog.LoginDialog;
import ph.txtdis.model.Users;
import ph.txtdis.type.LoginType;

public class LoginButton extends TextButton<Users> {
    
	public LoginButton(LoginDialog app) {
		super("Log-in");
		button.setOnAction(event -> app.validate(LoginType.LOGIN));
	}
}
