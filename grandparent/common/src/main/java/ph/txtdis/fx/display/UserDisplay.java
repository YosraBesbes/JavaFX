package ph.txtdis.fx.display;

import javafx.scene.control.TextField;
import ph.txtdis.model.SystemUser;

public class UserDisplay extends TextField {

    public UserDisplay(SystemUser user) {
        setUser(user);
        setMaxWidth(120);
        setEditable(false);
        focusTraversableProperty().set(false);
    }

    public void setUser(SystemUser user) {
        setText(user == null ? "" : user.toString());
    }
}
