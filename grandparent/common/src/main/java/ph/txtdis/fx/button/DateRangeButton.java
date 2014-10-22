package ph.txtdis.fx.button;

import java.time.LocalDate;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DateRanged;
import ph.txtdis.fx.dialog.DateRangeDialog;

public class DateRangeButton extends FontButton<Object> {

    public DateRangeButton(Apped app, DateRanged dto) {
        super("\ue807", "Set dates...");
        button.setOnAction(event -> {
            LocalDate[] dates = new DateRangeDialog(app.getModule(), (Stage) app, dto).getDates();
            dto.setStartDate(dates[0]);
            dto.setEndDate(dates[1]);
            app.refresh();
            app.setFocus();
        });
    }
}
