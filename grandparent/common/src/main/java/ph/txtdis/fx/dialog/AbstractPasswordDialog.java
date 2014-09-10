package ph.txtdis.fx.dialog;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import ph.txtdis.fx.util.FX;
import ph.txtdis.service.UserService;
import ph.txtdis.util.Login;

public abstract class AbstractPasswordDialog extends AbstractInputDialog<Object> {
    protected PasswordField password1, password2;

    public AbstractPasswordDialog(String name, UserService service) {
        super(name, null, service);
        FX.decorateWindow(this);        
        initStyle(StageStyle.DECORATED);
    }

    @Override
    protected void populateGrid(GridPane gridPane, Object object) {
        Label passwordLabel1 = createLabel("Enter Password");
        password1 = new PasswordField();
        Label passwordLabel2 = createLabel("Re-Type Password");
        password2 = new PasswordField();
        gridPane.add(passwordLabel1, 0, 1);
        gridPane.add(password1, 1, 1);
        gridPane.add(passwordLabel2, 0, 2);
        gridPane.add(password2, 1, 2);
    }

    protected Label createLabel(String name) {
        Label label = new Label(name);
        label.setStyle("-fx-font-size: 11pt; ");
        return label;
    }

    @Override
    protected Button[] createButtons(Object object) {
        return new Button[] { createCloseButton("OK") };
    }

    @Override
    protected void process() {
        if ((password1.getText()).equals(getPassword2())) {
            saveUser();
            close();
        } else {
            new ErrorDialog(this, "Passwords do not match");
            clearFields();
        }
    }

    protected void saveUser() {
        Login.user().setPassword(getPassword2());
        ((UserService) object).save(Login.user());
    }

    protected String getPassword2() {
        return password2.getText();
    }

    protected void clearFields() {
        password1.clear();
        password2.clear();
        password1.requestFocus();
    }
}