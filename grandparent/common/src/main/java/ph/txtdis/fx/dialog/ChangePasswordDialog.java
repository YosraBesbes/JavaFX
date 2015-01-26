package ph.txtdis.fx.dialog;

import org.springframework.context.ConfigurableApplicationContext;

public class ChangePasswordDialog extends AbstractPasswordDialog {
    public ChangePasswordDialog(ConfigurableApplicationContext context) {
        super("Change Password", context);
    }
}