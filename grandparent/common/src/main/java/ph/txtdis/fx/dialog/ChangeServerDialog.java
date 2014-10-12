package ph.txtdis.fx.dialog;

import ph.txtdis.service.UserService;

public class ChangeServerDialog extends AbstractServerDialog {
    public ChangeServerDialog(UserService service) {
        super("Change Server", service);
    }
}