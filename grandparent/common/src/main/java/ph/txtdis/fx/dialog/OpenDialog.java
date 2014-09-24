package ph.txtdis.fx.dialog;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.util.FX;

public class OpenDialog<E> extends AbstractInputDialog<Audited<E>> {
    private IdField idField;
    private int id;

    public OpenDialog(String name, Stage stage, Audited<E> dto) {
        super(name + " Data Retrieval", stage, dto);
        showAndWait();
    }

    @Override
    protected void populateGrid(GridPane gridPane, Audited<E> dto) {
        Label idLabel = new Label("ID No.");
        idField = new IdField();
        idField.setEditable(true);
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
    }

    @Override
    protected Button[] createButtons(Audited<E> dto) {
        Button openButton = createOpenButton(dto);
        Button closeButton = createCloseButton();
        return new Button[] { openButton, closeButton };
    }

    private Button createOpenButton(Audited<E> dto) {
        Button openButton = FX.createLargeButton("Open");
        openButton.setOnAction(event -> {
            setId(idField.getIdNo());
            close();
        });
        openButton.disableProperty().bind(idField.textProperty().isEmpty());
        return openButton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
