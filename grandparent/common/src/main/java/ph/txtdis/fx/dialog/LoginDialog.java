package ph.txtdis.fx.dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.fx.button.ServerButton;
import ph.txtdis.fx.button.LoginButton;
import ph.txtdis.fx.button.PasswordButton;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.util.FX;
import ph.txtdis.service.UserService;
import ph.txtdis.type.LoginType;
import ph.txtdis.util.Login;

public class LoginDialog extends Stage {

    private boolean isValid;
    private final PasswordField passwordField;
    private final StringField userField;
    private final UserService service;
    private static int tries;
    private LoginType type;

    public LoginDialog(UserService service) {
        this.service = service;

        Label userLabel = new Label("Username");
        userField = new StringField();

        Label passwordLabel = new Label("Password");
        passwordField = new PasswordField();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.setPadding(new Insets(0, 0, 10, 0));

        Button loginButton = new LoginButton(this).getButton();
        Button addButton = new ServerButton(this).getButton();
        Button editButton = new PasswordButton(this).getButton();

        HBox buttonBox = new HBox(loginButton, addButton, editButton);

        Label logo = new Label("\ue601");
        logo.setStyle("-fx-font: 72 'icomoon'; -fx-text-fill: navy;");
        logo.setPadding(new Insets(10));

        ProgressIndicator indicator = new ProgressIndicator(-1.0);
        indicator.setScaleX(2.0);
        indicator.setScaleY(2.0);

        StackPane stackPane = new StackPane(logo, indicator);

        VBox messageBox = new VBox(gridPane, buttonBox);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setPadding(new Insets(0, 0, 0, 50));

        HBox dialogBox = new HBox(stackPane, messageBox);
        dialogBox.setPadding(new Insets(30, 20, 30, 50));
        dialogBox.setStyle("-fx-border-color: -fx-base; -fx-background-color: #aaaaff; -fx-accent: white; ");
        dialogBox.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(dialogBox);
        FX.decorateWindow(this);
        setScene(dialogScene);
        getScene().getStylesheets().addAll("/css/base.css");
        setTitle("Welcome to txtDIS!");
        userField.requestFocus();
    }

    public void validate(LoginType type) {
        this.type = type;
        if (isUsernameAndPasswordIncorrect())
            retryThrice();
        else
            setAsValid();
    }

    private void setAsValid() {
        close();
        isValid = true;
    }

    private void retryThrice() {
        new ErrorDialog(this, "Incorrect username\nand/or password");
        clearFields();
        if (++tries > 2)
            close();
    }

    private boolean isUsernameAndPasswordIncorrect() {
        return null == Login.validate(service, userField.getText(), passwordField.getText());
    }

    private void clearFields() {
        userField.clear();
        passwordField.clear();
        userField.requestFocus();
    }

    public boolean isValid() {
        return isValid;
    }

    public LoginType getType() {
        return type;
    }
}
