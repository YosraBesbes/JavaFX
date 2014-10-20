package ph.txtdis.fx.button;

import java.time.LocalDate;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DateRanged;
import ph.txtdis.fx.dialog.DateRangeDialog;
import ph.txtdis.fx.dialog.ProgressDialog;

public class DateRangeButton extends FontButton<Object> {

    public DateRangeButton(Apped app, DateRanged dto) {
        super("\ue807", "Set dates...");
        button.setOnAction(event -> {
            LocalDate[] dates = new DateRangeDialog(app.getModule(), (Stage) app, dto).getDates();
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    dto.setStartDate(dates[0]);
                    dto.setEndDate(dates[1]);
                }

                @Override
                protected void next() {
                    app.refresh();
                    app.setFocus();
                }
            };
        });
    }
}
