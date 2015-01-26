package ph.txtdis.fx.dialog;

import ph.txtdis.fx.button.TextButton;
import ph.txtdis.model.Users;
import ph.txtdis.type.LoginType;

public class PasswordButton extends TextButton<Users> {

	public PasswordButton(LoginDialog app) {
		super("Alter Password");
        button.setOnAction(event -> app.validate(LoginType.CHANGE));
	}
}
