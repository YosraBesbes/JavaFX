package ph.txtdis.fx.dialog;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.app.Searched;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.util.FX;
import ph.txtdis.util.Util;

public class SearchDialog extends AbstractInputDialog<String> {
    private StringField textField;
    private String text;

    public SearchDialog(Apped app) {
        super("Find a(n) " + Util.getModule(app), (Stage) app, ((Searched) app).setSearchedCriteria());
        showAndWait();
    }

    @Override
    protected void populateGrid(GridPane gridPane, String criteria) {
        Label help = new Label("Enter partial or full " + criteria + " to find a match; blank to list all");
        textField = new StringField();
        gridPane.add(help, 0, 0);
        gridPane.add(textField, 0, 1);
    }

    @Override
    protected Button[] createButtons(String criteria) {
        Button findButton = createOpenButton(criteria);
        Button closeButton = createCloseButton();
        return new Button[] { findButton, closeButton };
    }

    private Button createOpenButton(String criteria) {
        Button findButton = FX.createLargeButton("Find");
        findButton.setOnAction(event -> {
            text = textField.getText();
            close();
        });
        return findButton;
    }

    public String getText() {
        return text;
    }
}
