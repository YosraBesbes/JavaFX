package ph.txtdis.fx.button;

import java.time.LocalDate;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DTO;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.dialog.OpenByDateDialog;

public class OpenByDateButton<E> extends FontButton<E> {

    public OpenByDateButton(Apped app, DTO<E, LocalDate> dto) {
        super("\ue807", "Open...");
        button.setOnAction(event -> {
            LocalDate date = new OpenByDateDialog<>(app.getModule(), (Stage) app, dto).getDate();
            if (dto.exists(date)) {
                dto.setById(date);
                app.refresh();
                app.setFocus();
            } else
                new ErrorDialog((Stage) app, app.getModule() + " dated " + date + "\nwas not found.");
        });
    }
}
