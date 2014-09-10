package ph.txtdis.fx.dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.fx.util.FX;

public abstract class AbstractMessageDialog extends AbstractDialog<String[]> {

    public AbstractMessageDialog(Stage stage, String message, String unicode, String color) {
        super(message, stage, new String[] { unicode, color });
        show();
    }

    @Override
    protected Node[] addVBoxNodes(String messageText, String[] iconProperties) {
        String name = iconProperties[0];
        String color = iconProperties[1];

        Label icon = new Label(name);
        icon.setStyle("-fx-font: 108pt 'icomoon'; -fx-text-fill: " + color + ";");

        Label message = new Label(messageText);
        message.setStyle("-fx-font-size: 11pt; ");

        Button closeButton = FX.createLargeButton("OK");
        closeButton.setOnAction(event -> close());

        HBox buttonBox = new HBox(closeButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        VBox messageBox = new VBox(message, buttonBox);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setPadding(new Insets(0, 0, 0, 20));

        HBox dialogBox = new HBox(icon, messageBox);

        return new Node[] { dialogBox };
    }
}
