package ph.txtdis.fx.button;

import org.springframework.context.ConfigurableApplicationContext;

import ph.txtdis.fx.dialog.AddUserDialog;

public class UserButton extends FontButton<Object> {

    public <C> UserButton(ConfigurableApplicationContext context) {
        super("\ue82d", "System User", 44);
        button.setOnAction(event -> new AddUserDialog(context).showAndWait());
    }
}
