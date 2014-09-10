package ph.txtdis.fx.dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class AbstractDialog<D> extends Stage {
    protected final Stage stage;
    protected final D object;

    public AbstractDialog(String name, Stage stage, D dto) {
        this.stage = stage;
        this.object = dto;

        initModality(Modality.WINDOW_MODAL);
        initStyle(StageStyle.TRANSPARENT);
        initOwner(stage);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(addVBoxNodes(name, dto));
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0, 20, 0, 20));
        vbox.setStyle(" -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 0.5em;"
                + " -fx-background-radius: 0.5em;");
        Scene dialogScene = new Scene(vbox);
        dialogScene.setFill(Color.TRANSPARENT);
        setScene(dialogScene);
    }

    protected abstract Node[] addVBoxNodes(String name, D dto);

    public Stage getStage() {
        return stage;
    }
}
