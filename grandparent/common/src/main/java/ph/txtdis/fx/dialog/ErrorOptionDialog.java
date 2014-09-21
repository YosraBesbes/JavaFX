package ph.txtdis.fx.dialog;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import ph.txtdis.fx.util.FX;

public abstract class ErrorOptionDialog extends ErrorDialog {

    private boolean affirmative;

    public ErrorOptionDialog(Stage stage, String message) {
        super(stage, message);
    }

    @Override
    protected Button[] addButtons() {
        Button yesButton = FX.createLargeButton("Yes");
        yesButton.setOnAction(event -> handleAffirmation());

        Button noButton = FX.createLargeButton("No");
        noButton.setOnAction(event -> close());

        return new Button[] { yesButton, noButton };
    }

    protected void handleAffirmation() {
        affirmative = true;
        close();
    }

    public boolean isAffirmative() {
        return affirmative;
    }
}
