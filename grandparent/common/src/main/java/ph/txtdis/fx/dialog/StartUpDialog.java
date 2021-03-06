package ph.txtdis.fx.dialog;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ph.txtdis.fx.util.FX;
import ph.txtdis.fx.util.FontToImage;

public abstract class StartUpDialog extends Stage {

    public StartUpDialog() {
        init();
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                begin();
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                close();
                next();
            }
        };

        new Thread(task).start();
    }

    private void init() {
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UNDECORATED);
        FX.loadIcomoon();
        FX.loadTxtdisIcons();
        FX.loadFont("Ubuntu-BI");

        Label trademark = new Label("txtDIS");
        trademark.setStyle("-fx-font: 38pt 'ubuntu'; -fx-text-fill: navy;");
        trademark.setAlignment(Pos.BOTTOM_CENTER);

        Label text = new Label("Starting app...");
        text.setStyle("-fx-font-size: 26pt;");
        text.setAlignment(Pos.TOP_CENTER);

        Label logo = new Label("\ue601");
        logo.setStyle("-fx-font: 72 'icomoon'; -fx-text-fill: navy;");
        logo.setPadding(new Insets(10));

        ProgressIndicator indicator = new ProgressIndicator(-1.0);
        indicator.setScaleX(2.0);
        indicator.setScaleY(2.0);

        StackPane stackPane = new StackPane(logo, indicator);

        VBox messageBox = new VBox(trademark, text);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setPadding(new Insets(0, 0, 0, 50));

        HBox dialogBox = new HBox(stackPane, messageBox);
        dialogBox.setPadding(new Insets(30, 20, 30, 50));
        dialogBox.setStyle("-fx-border-color: -fx-base; -fx-background-color: #aaaaff; -fx-accent: white; ");

        Image icon = new FontToImage("icomoon", "\ue601", Color.NAVY).getImage();

        Scene dialogScene = new Scene(dialogBox);
        dialogScene.getStylesheets().addAll("/css/base.css");
        getIcons().add(icon);
        setScene(dialogScene);
        show();
    }

    protected abstract void begin();

    protected abstract void next();
}
