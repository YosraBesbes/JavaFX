package ph.txtdis.fx.button;

import java.time.LocalDate;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.AbstractSpunByDate;
import ph.txtdis.dto.Audited;
import ph.txtdis.dto.DTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.util.Util;

public class SaveButton<E, K> extends FontButton<E> {
    public SaveButton(Apped app, DTO<E, K> dto) {
        super("\ue823", "Save...");
        button.setOnAction(event -> trySaving(app, dto));
    }

    private void trySaving(Apped app, DTO<E, K> dto) {
        try {
            app.save();
            new InfoDialog((Stage) app, "Successfully posted data of\n" + getIdAndName(app, dto));
        } catch (InvalidException e) {
            new ErrorDialog((Stage) app, e.getMessage());
        } finally {
            app.refresh();
        }
    }

    private String getIdAndName(Apped app, DTO<E, K> dto) {
        if (dto instanceof Audited)
            return Util.getEntityIdAndName(app, (Audited<?>) dto);
        else if (dto instanceof AbstractSpunByDate<?, ?>)
            return Util.getModule(app) + "\ndated " + Util.formatDate((LocalDate) dto.getId());
        else
            return "";
    }
}
