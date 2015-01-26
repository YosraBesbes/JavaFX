package ph.txtdis.fx.display;

import javafx.scene.control.TextField;
import ph.txtdis.model.Users;

public class UserDisplay extends TextField {

    public UserDisplay(Users user) {
        setUser(user);
        setMaxWidth(120);
        setEditable(false);
        focusTraversableProperty().set(false);
    }

    public void setUser(Users user) {
        setText(user == null ? "" : user.toString());
    }
}
