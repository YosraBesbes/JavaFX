package ph.txtdis.fx.dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ph.txtdis.fx.util.FX;

public abstract class AbstractInputDialog<D> extends AbstractDialog<D> {
    protected Button closeButton;

    public AbstractInputDialog(String name, Stage stage, D dto) {
        super(name, stage, dto);
    }

    @Override
    protected Node[] addVBoxNodes(String name, D dto) {
        Label label = new Label(name);
        label.setStyle("-fx-font-size: 26pt;");
        label.setPadding(new Insets(20, 0, 0, 0));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        populateGrid(gridPane, dto);

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(createButtons(dto));
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(0, 0, 20, 0));
        return new Node[] { label, gridPane, buttonBox };
    }

    protected Button createCloseButton() {
        return createCloseButton("Close");
    }

    protected Button createCloseButton(String name) {
        closeButton = FX.createLargeButton(name);
        closeButton.setOnAction(event -> process());
        return closeButton;
    }

    protected void process() {
        close();
    }

    protected abstract void populateGrid(GridPane gridPane, D dto);

    protected abstract Button[] createButtons(D dto);
}
