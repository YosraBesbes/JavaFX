package ph.txtdis.fx.button;

import ph.txtdis.fx.dialog.LoginDialog;
import ph.txtdis.model.SystemUser;
import ph.txtdis.type.LoginType;

public class PasswordButton extends TextButton<SystemUser> {

	public PasswordButton(LoginDialog app) {
		super("Alter Password");
        button.setOnAction(event -> app.validate(LoginType.CHANGE));
	}
}
