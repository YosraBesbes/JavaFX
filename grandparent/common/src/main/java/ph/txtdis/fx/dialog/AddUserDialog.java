package ph.txtdis.fx.dialog;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import org.springframework.context.ConfigurableApplicationContext;

import ph.txtdis.exception.DuplicateException;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Users;
import ph.txtdis.service.UserService;

public class AddUserDialog extends AbstractPasswordDialog {
    private StringField userField;

    public AddUserDialog(ConfigurableApplicationContext context) {
        super("Add New User", context);
        closeButton.disableProperty().bind(FX.isEmpty(userField).or(FX.isEmpty(password1)).or(FX.isEmpty(password2)));
    }

    @Override
    protected void populateGrid(GridPane gridPane, Object object) {
        Label userLabel = createLabel("Input Username");
        userField = new StringField();
        userField.focusedProperty().addListener(addFocusListener());
        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        super.populateGrid(gridPane, object);
    }

    private ChangeListener<Boolean> addFocusListener() {
        return (observable, oldValue, newValue) -> {
            if (newValue)
                clearPasswordFields();
            else
                isValid();
        };
    }

    private boolean isValid() {
        try {
            return validateUserName(userField.getText());
        } catch (DuplicateException e) {
            new ErrorDialog(getStage(), e.getMessage());
            clearFields();
            return false;
        }
    }

    private boolean validateUserName(String username) throws DuplicateException {
        if (doesExist(username)) {
            throw new DuplicateException(username);
        } else {
            return true;
        }
    }

    private boolean doesExist(String username) {
        return ((UserService) object).exists(username);
    }

    @Override
    protected void saveUser() {
        ((UserService) object).save(createNewUser());
    }

    private Users createNewUser() {
        return new Users(getUserName(), getPassword2(), true);
    }

    private String getUserName() {
        return userField.getText();
    }

    @Override
    protected void clearFields() {
        clearPasswordFields();
        userField.clear();
        userField.requestFocus();
    }

    private void clearPasswordFields() {
        password1.clear();
        password2.clear();
    }
}