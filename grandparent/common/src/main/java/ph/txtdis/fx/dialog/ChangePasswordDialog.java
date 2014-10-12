package ph.txtdis.fx.dialog;

import ph.txtdis.service.UserService;

public class ChangePasswordDialog extends AbstractPasswordDialog {
    public ChangePasswordDialog(UserService service) {
        super("Change Password", service);
    }
}