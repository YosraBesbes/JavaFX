package ph.txtdis.fx.dialog;

import java.time.LocalDate;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ph.txtdis.dto.DateRanged;
import ph.txtdis.fx.util.FX;

public class DateRangeDialog extends AbstractInputDialog<DateRanged> {
    private DatePicker startDatePicker, endDatePicker;
    private LocalDate[] dates;

    public DateRangeDialog(String name, Stage stage, DateRanged dto) {
        super(name + " Date Range Entry", stage, dto);
        setListeners();
        showAndWait();
    }

    @Override
    protected void populateGrid(GridPane gridPane, DateRanged dto) {
        Label startLabel = new Label("Start Date");
        startDatePicker = new DatePicker();
        Label endLabel = new Label("End Date");
        endDatePicker = new DatePicker();
        gridPane.add(startLabel, 0, 0);
        gridPane.add(startDatePicker, 1, 0);
        gridPane.add(endLabel, 0, 1);
        gridPane.add(endDatePicker, 1, 1);
    }

    private void setListeners() {
        endDatePicker.setOnAction(endDate -> {
            if (endDatePicker.getValue().isBefore(startDatePicker.getValue()))
                new ErrorDialog(stage, "End date cannot be before start");
        });
    }

    @Override
    protected Button[] createButtons(DateRanged dto) {
        return new Button[] { createOpenButton(), createCloseButton() };
    }

    private Button createOpenButton() {
        Button openButton = FX.createLargeButton("Show");
        openButton.setOnAction(event -> {
            setDates(new LocalDate[] { startDatePicker.getValue(), endDatePicker.getValue() });
            close();
        });
        openButton.disableProperty().bind(FX.isEmpty(startDatePicker).or(FX.isEmpty(endDatePicker)));
        return openButton;
    }

    public LocalDate[] getDates() {
        return dates;
    }

    private void setDates(LocalDate[] dates) {
        this.dates = dates;
    }
}
