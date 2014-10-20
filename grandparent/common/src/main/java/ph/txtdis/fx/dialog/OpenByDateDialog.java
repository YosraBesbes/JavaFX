package ph.txtdis.fx.dialog;

import java.time.LocalDate;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ph.txtdis.dto.DTO;
import ph.txtdis.fx.util.FX;

public class OpenByDateDialog<E> extends AbstractInputDialog<DTO<E, LocalDate>> {
    private DatePicker datePicker;
    private LocalDate date;

    public OpenByDateDialog(String name, Stage stage, DTO<E, LocalDate> dto) {
        super(name + " Data Retrieval", stage, dto);
        showAndWait();
    }

    @Override
    protected void populateGrid(GridPane gridPane, DTO<E, LocalDate> dto) {
        Label idLabel = new Label("Date");
        datePicker = new DatePicker();
        gridPane.add(idLabel, 0, 0);
        gridPane.add(datePicker, 1, 0);
    }

    @Override
    protected Button[] createButtons(DTO<E, LocalDate> dto) {
        Button openButton = createOpenButton(dto);
        Button closeButton = createCloseButton();
        return new Button[] { openButton, closeButton };
    }

    private Button createOpenButton(DTO<E, LocalDate> dto) {
        Button openButton = FX.createLargeButton("Open");
        openButton.setOnAction(event -> {
            setDate(datePicker.getValue());
            close();
        });
        openButton.disableProperty().bind(FX.isEmpty(datePicker));
        return openButton;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }
}
