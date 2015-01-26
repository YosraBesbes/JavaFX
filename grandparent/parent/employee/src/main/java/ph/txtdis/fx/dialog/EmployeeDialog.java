package ph.txtdis.fx.dialog;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import org.springframework.context.ConfigurableApplicationContext;

import ph.txtdis.fx.button.BackupButton;
import ph.txtdis.fx.button.HRButton;
import ph.txtdis.fx.button.StyleButton;
import ph.txtdis.fx.button.UserButton;
import ph.txtdis.fx.util.FX;
import ph.txtdis.fx.util.StyleSheet;
import ph.txtdis.model.Style;
import ph.txtdis.model.Users;
import ph.txtdis.service.StyleService;
import ph.txtdis.util.Login;

public class EmployeeDialog extends Stage {

    private StyleService styleService;
    private StyleSheet styleSheet = new StyleSheet();

    public EmployeeDialog(ConfigurableApplicationContext context) {

        styleService = context.getBean(StyleService.class);
        setDefaultStyle(Login.user());

        Button backupButton = new BackupButton(this).getButton();
        Button hrButton = new HRButton(this).getButton();
        Button styleButton = new StyleButton(this, styleSheet, styleService).getButton();
        Button userButton = new UserButton(context).getButton();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(hrButton, 0, 0);
        gridPane.add(userButton, 1, 0);
        gridPane.add(styleButton, 2, 0);
        gridPane.add(backupButton, 3, 0);

        gridPane.add(getLabel("Employee"), 0, 1);
        gridPane.add(getLabel("Add User"), 1, 1);
        gridPane.add(getLabel("Change UI"), 2, 1);
        gridPane.add(getLabel("Backup"), 3, 1);

        HBox dialogBox = new HBox(gridPane);
        dialogBox.setPadding(new Insets(10));
        dialogBox.setStyle("-fx-border-color: -fx-base; -fx-background-color: #aaaaff; -fx-accent: white; ");
        dialogBox.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(dialogBox);
        FX.putIconAndTitle(this);
        setScene(dialogScene);

        getScene().getStylesheets().addAll("/css/base.css");
        setTitle("Welcome to txtDIS!");
    }

    private Label getLabel(String name) {
        Label label = new Label(name);
        GridPane.setHalignment(label, HPos.CENTER);
        return label;
    }

    private void setDefaultStyle(Users user) {
        Style style = styleService.get(user);
        if (style != null)
            styleSheet.update(style);
    }
}
